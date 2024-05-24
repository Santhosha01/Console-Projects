package com.santhosh.librarymanagement.databaseManagemet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.Credentials;
import com.santhosh.librarymanagement.model.Library;
import com.santhosh.librarymanagement.model.User;

public class LibraryDatabase {
	private static LibraryDatabase libraryDatabase;
	private Library library;
	private List<Book> books;
	private List<User> userList;
	private List<Map<String, Book>> issuedBooks;
	private Credentials credential;
	private List<Credentials> userCredentials;
	private String bookFile = "C:\\Users\\dell\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\book.json";
	private String userFile = "C:\\Users\\dell\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\user.json";
	private String mappedBookFile = "C:\\Users\\dell\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\mappedBooks.json";
	private String UserCredentials = "C:\\Users\\dell\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\UserCredentials.json";
	private String libraryFile = "C:\\Users\\dell\\eclipse-workspace\\InterviewPanelManagement\\src\\com\\santhosh\\Interviewpanelmanagement\\JsonFile\\Library.json";

	ObjectMapper mapper = new ObjectMapper();

	private LibraryDatabase() {

		this.books = new ArrayList<>();
		this.userList = new ArrayList<>();
		this.issuedBooks = new ArrayList<>();
		this.userCredentials = new ArrayList<>();
	}

	public static LibraryDatabase getInstance() {
		if (libraryDatabase == null) {
			libraryDatabase = new LibraryDatabase();
		}
		return libraryDatabase;
	}

	public Library getLibrary() {
		ObjectMapper libraryObj = new ObjectMapper();
		Path path = Paths.get(libraryFile);
//		if (Files.exists(path)) {
			try {
				library = libraryObj.readValue(new File(libraryFile), Library.class);
				return libraryObj.readValue(new File(libraryFile), Library.class);

			} catch (Exception e) {
				return null;
			}
//		}
//		return null;
	}

	public Library getLibraryDetails() {
		return library;
	}

	public void insertLibrary(Library library) {
		this.library = library;
		try {
			File file = new File(libraryFile);
//			if (!file.exists()) {
//				file.createNewFile();
//			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, library);
		} catch (Exception e) {
		}
	}

	public boolean insertBooks(Book book) {
		ObjectMapper bookObj = new ObjectMapper();
		try {
			File fileCandidate = new File(bookFile);
			if (!fileCandidate.exists()) {
				fileCandidate.createNewFile();
			}
			if (fileCandidate.length() > 0) {
				books = bookObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
				});
				for (Book b : books) {
					if (b.getName().equals(book.getName()))
						return false;
				}

				books.add(book);
				writeData(books);
				return true;
			} else {
				books.add(book);
				writeData(books);
				return true;
			}
		} catch (Exception e) {
			System.out.println("interviewer");
		}
		return false;

	}

	public void writeData(List<Book> books) {
		ObjectMapper obj = new ObjectMapper();
		File f = new File(bookFile);
		try {
			obj.writeValue(f, books);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> showBooks() {
		ObjectMapper bookObj = new ObjectMapper();
		Path path = Paths.get(bookFile);
		if (Files.exists(path)) {
			try {
				return books = bookObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} 
		return null;
	}

	public boolean insertUser(User user) {

		ObjectMapper userObj = new ObjectMapper();
		try {
			File fileCandidate = new File(userFile);
			if (!fileCandidate.exists()) {
				System.out.println("file");
				fileCandidate.createNewFile();
			}
			if (fileCandidate.length() > 0) {
				userList = userObj.readValue(new File(userFile), new TypeReference<List<User>>() {
				});
				for (User u : userList) {
					if (u.getMailId().equals(user.getMailId()))
						return false;
				}
				userList.add(user);
				writeUserData(userList);
				return true;
			} else {

				userList.add(user);
//				userObj.writeValue(fileCandidate, userList);
				writeUserData(userList);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User");
		}
		return false;

	}

	public void writeUserData(List<User> users) {
		ObjectMapper obj = new ObjectMapper();
		File f = new File(userFile);
		try {
			obj.writeValue(f, users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeandUpdateUser(User updateUser) {
		ObjectMapper userObj = new ObjectMapper();
		Path path = Paths.get(userFile);
		if (Files.exists(path)) {
			try {
				userList = userObj.readValue(new File(userFile), new TypeReference<List<User>>() {
				});
				for (User u : userList) {
					if (u.getUserName().equals(updateUser.getUserName())) {
						FileWriter file = new FileWriter(userFile, false);
						userList.remove(u);
						writeUserData(userList);
						break;
					}
				}
				for(Credentials cre:userCredentials) {
					if(cre.getUserName().equals(updateUser.getUserName())) {
						userCredentials.remove(cre);
						writeCredentails(userCredentials);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<User> showUsers() {
		ObjectMapper userObj = new ObjectMapper();
		Path path = Paths.get(userFile);
		if (Files.exists(path)) {
			try {
				return userList = userObj.readValue(new File(userFile), new TypeReference<List<User>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void insertIssuedBooks(Map<String, Book> mapbooktocustomer) {
		try {
			File file = new File(mappedBookFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			else {
			   issuedBooks = mapper.readValue(file,
					new TypeReference<List<Map<String, Book>>>() {
					});
			}
			issuedBooks.add(mapbooktocustomer);
			mapper.writeValue(file, issuedBooks);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Map<String, Book>> showIssuedBooks() {
		Path path = Paths.get(mappedBookFile);
		if (Files.exists(path)) {
			try {
				issuedBooks = mapper.readValue(new File(mappedBookFile), new TypeReference<List<Map<String, Book>>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return issuedBooks;
	}

	public void insertCredentials(Credentials credential) {
		this.credential = credential;
	}

	public Credentials getCredentials() {
		return credential;
	}

	public void insertUserCredentials(Credentials credential) {
		try {
			File file = new File(UserCredentials);
			if (!file.exists()) {
				file.createNewFile();
			}
			else {
			userCredentials = mapper.readValue(file, new TypeReference<List<Credentials>>() {
			});
			}
			userCredentials.add(credential);
			writeCredentails(userCredentials);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeCredentails(List<Credentials> userCredentials) {
		ObjectMapper obj = new ObjectMapper();
		File f = new File(UserCredentials);
		try {
			obj.writeValue(f, userCredentials);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Credentials> getUserCredentials() {
		Path path = Paths.get(UserCredentials);
		if (Files.exists(path)) {
			try {
				userCredentials = mapper.readValue(new File(UserCredentials), new TypeReference<List<Credentials>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userCredentials;
	}

	public void updateBook(Book updateBook) {
		ObjectMapper bookObj = new ObjectMapper();
		Path path = Paths.get(bookFile);
		if (Files.exists(path)) {
			try {
				books = bookObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
				});
			writeData(books);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteBook(Book deleteBook) {
		ObjectMapper deleteObj = new ObjectMapper();
		Path path = Paths.get(bookFile);
		if (Files.exists(path)) {
			try {
				books = deleteObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
				});
				;
				for (Book b : books) {
					if (b.getId() == (deleteBook.getId())) {
						books.remove(b);
						writeData(books);
						break;
					}
				}
				writeData(books);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
