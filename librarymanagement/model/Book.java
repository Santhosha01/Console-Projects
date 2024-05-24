package com.santhosh.librarymanagement.model;

import java.util.Date;

public class Book {
	private String bookName;
	private int bookId;
	private String author;
	private String publication;
	private String edition;
	private int availableCount;
	private int volume;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String currentDate) {
		this.date = currentDate;
	}

	public String getName() {
		return bookName;
	}

	public void setName(String name) {
		this.bookName = name;
	}
	 
   public int getId() {
	   return bookId;
   }
	public void setId(int bookId) {
		this.bookId =bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "\n  Book Details : \n\n BookName = " + bookName +"\n Book ID = " + bookId+ "\n Author = " + author
				+ "\n Publication Year = " + publication + "\n Edition = " + edition + "\n Available Count = "
				+ availableCount + "\n Volume = " + volume + "\n Issued Date = " + date;
	}

}
