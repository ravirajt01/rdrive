package com.ss.bean;

import com.cft.pojo.BookTour;

public interface Communicable {

	public void suscribe(String email);

	void sendTourBookMail(BookTour bookTour);

}
