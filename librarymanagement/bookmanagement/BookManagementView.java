package com.santhosh.librarymanagement.bookmanagement;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.librarysetup.LibrarySetupView;
import com.santhosh.librarymanagement.manageUser.UserView;
import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.User;

public class BookManagementView {
	BookManagementModel bookManagementModel;
	Scanner sc = new Scanner(System.in);
	static int bookid = 1;

	public BookManagementView() {
		this.bookManagementModel = new BookManagementModel(this);
	}

	public void getBookDetails() {
		try {
			System.out.println("Enter the Name of the Book");
			String bookName = sc.nextLine();

			System.out.println("Enter the Author of the Book");
			String author = sc.nextLine();

			System.out.println("Enter the Publication Year of the Book");
			String publish = sc.nextLine();

			System.out.println("Enter the Edition of the Book");
			String edition = sc.nextLine();

			System.out.println("Enter the Available Count of the Book");
			int availableCount = sc.nextInt();

			System.out.println("Enter the Volume of the Book");
			int volume = sc.nextInt();
			sc.nextLine();
			bookManagementModel.setBookDetails(bookName, author, publish, edition, availableCount, volume);
		} catch (Exception e) {
			getBookDetails();
		}
	}

	public void showBooks() {
		System.out.println("\nList of Books\n");
		int i = 1;
		List<Book> books = LibraryDatabase.getInstance().showBooks();
		if (books != null) {
			for (Book book : books) {
				System.out.println(i++ + "." + book.getName());
			}
		}
	}

	public void searchBook() {
		System.out.println("Enter the Name of the Book you want to Search");
		String search = sc.nextLine();
		Book searchBook = bookManagementModel.searchBookDetails(search);
		if (searchBook != null) {
			System.out.println(searchBook.toString());
		} else {
			System.out.println("Book not found");
		}
	}

	public boolean getCredentails() {
		System.out.println("Enter your Admin name");
		String name = sc.nextLine();
		System.out.println("Enter your Password");
		String password = sc.nextLine();
		return bookManagementModel.checkAdmin(name, password);
	}

	public void deleteBook() {
		System.out.println("Enter the Book Name you want to delete");
		String deleteBook = sc.nextLine();
		bookManagementModel.deleteBook(deleteBook);
	}

	public void alertBook() {
		System.out.println("Book not found");
	}

	public void onSuccess() {
		System.out.println("Progress Successfully completed");
	}

	public void updateBook() {
		System.out.println("Enter the Name of the Book you want to Update");
		String updatebook = sc.nextLine();
		while (true) {
			System.out.println("What you need to Update in the Book");
			System.out.println(
					"\n 1.Update Book Edition \n 2.Update Book Count \n 3.Update Book Volume \n 4.Exit Updation Process \nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter the Updated Edition of the Book");
				String edition = sc.nextLine();
				bookManagementModel.updateBookEdition(updatebook, edition);
				break;
			case 2:
				System.out.println("Enter the Updated Count of the Book");
				int count = sc.nextInt();
				sc.nextLine();
				bookManagementModel.updateBookCount(updatebook, count);
				break;
			case 3:
				System.out.println("Enter the Updated Volume of the Book");
				int volume = sc.nextInt();
				sc.nextLine();
				bookManagementModel.updateBookVolume(updatebook, volume);
				break;
			case 4:
				System.out.println("\n-- Updation Completed-----");
				new LibrarySetupView().onSetupComplete();
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}
	}

	public void issueBook(String userName) {
//		System.out.println("Enter your User Name");
//		String userName = sc.nextLine();
//		System.out.println("Enter your password");
//		String password = sc.nextLine();
		List<User> users = LibraryDatabase.getInstance().showUsers();
		String password = "";
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				password = user.getPassword();
			}
		}
		System.out.println("Enter the Name of the Book you want");
		String book = sc.nextLine();
		UserView userView = new UserView();
		Book issbook=bookManagementModel.searchBookDetails(book);
		if ((issbook.getAvailableCount()==0)&&bookManagementModel.searchBookDetails(book) == null && (userView.searchUser(userName, password))) {
			
			System.out.println("Book not found or User Id Not Found");
		} else {
			Book takeBook = bookManagementModel.searchBookDetails(book);
			takeBook.setId(bookid++);
			LocalDate date = LocalDate.now();
			String d=""+date;
			takeBook.setDate(d);
		takeBook.setAvailableCount(takeBook.getAvailableCount() - 1);
		System.out.println(takeBook);
		bookManagementModel.mapBookandCustomer(userName, takeBook);
		System.out.println("Book Issued to the User");
		}
	}

	public void showIssuedBooks() {
		System.out.println("\nList of Issued Books\n");
		List<Map<String, Book>> issuedBooks = LibraryDatabase.getInstance().showIssuedBooks();
		if (issuedBooks.isEmpty()) {
			System.out.println("No Books");
		} else {
			for (Map<String, Book> book : issuedBooks) {
				System.out.println(book);
			}
		}
	}
}