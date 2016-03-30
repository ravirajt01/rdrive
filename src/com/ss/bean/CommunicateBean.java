package com.ss.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cft.pojo.BookTour;
import com.cft.pojo.MailDetails;
import com.cft.pojo.User;
import com.cft.pojo.Vender;

public class CommunicateBean /*implements Communicable */{

	
	private static String fromEmail = "carfortour@gmail.com" ;
	private static String fromPW = "CarForTour2015" ;
	
	private static String domainName = "http://localhost:8080";
    //private static String domainName = "http://carfortour.com";
	private static String userVerifyRegLink = "/rest/user/verify?tk=";
	private static String venderVerifyRegLink = "/rest/vender/verify?tk=";
	
	
	
	
	public enum MailType {TOUR_BOOK , TOUR_BOOK_ADMIN , VRIFY_REG, VRIFYED_REG, FORGOT_PASS, VVRIFY_REG, VVRIFYED_REG};
	
	private static String suscribtionSubject = "Subscribtion To CarForTour" ;
	private static String suscribtionBodyEncoded = "%0A%0A%3Chtml%3E%3Chead%3E%0A%3Ctitle%3Ess%3C%2Ftitle%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Cstyle%3E%0A%20%20%20%20body%20%7B%0A%09%09font-family%3A%20%27Open%20Sans%27%2C%20sans-serif%3B%0A%09%09position%3A%20relative%3B%0A%09%09margin%3A0%3B%0A%09%09font-size%3A10pt%3B%0A%09%7D%0A%3C%2Fstyle%3E%0A%3C%2Fhead%3E%0A%20%3Cbody%3E%0A%09%3Cdiv%20class%3D%27wrapper%27%3E%0A%09%09%3Ctable%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20border%3D%270%27%20style%3D%27margin%3A%200%20auto%3B%27%3E%0A%09%09%09%3Ctbody%3E%0A%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%3Ctd%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3Ctable%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20width%3D%27100%25%27%20height%3D%27100%25%27%20bgcolor%3D%27rgb(238%2C238%2C238)%27%20style%3D%27background%3Argb(238%2C238%2C238)%3Bwidth%3A100%25!important%3Bheight%3A100%25%3Bmargin%3A0%3Bpadding%3A0%3B%26gt%3B%0A%09%09%09%09%09%09%09%26lt%3Btbody%26gt%3B%0A%09%09%09%09%09%09%09%09%26lt%3Btr%26gt%3B%0A%09%09%09%09%09%09%09%09%09%26lt%3Btd%20align%3D%27%20center%27%3D%27%27%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Ca%20href%3D%27http%3A%2F%2Fcarfortour.com%27%20target%3D%27_blank%27%3E%3Cimg%20src%3D%27http%3A%2F%2Fi1190.photobucket.com%2Falbums%2Fz457%2Fravirajt01%2Fem_hd_zpsvz4fdxiw.png%27%20border%3D%270%27%20alt%3D%27%27%2F%3E%3C%2Fa%3E%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20style%3D%27border-bottom%3A%201px%20solid%20rgb(236%2C236%2C236)%3Bmin-height%3A%20200px%3Bfont-size%3A1.2em%3B%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(20%2C65%2C114)%3Bmargin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09Dear%20User%2C%20%3Cbr%3E%3Cbr%3E%3Cbr%3E%20Welcome%20to%20CFT%20-%20carfortour.com%20%3Cbr%3E%3Cbr%3EYour%20Subscribtion%20to%20CarForTour%20done%20successfully.%20We%20will%20notify%20our%20updates%20via%20email.%20%3Cbr%3E%3Cbr%3E%20Thank%20You%20!%0A%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(127%2C193%2C15)%3B%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(153%2C153%2C153)%3B%20%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%0A%09%09%09%09%09%09%0A%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%3C%2Ftbody%3E%09%09%09%0A%09%09%3C%2Ftable%3E%0A%09%3C%2Fdiv%3E%0A%20%0A%3C%2Fbody%3E%3C%2Fhtml%3E%0A%0A" ; 
	

	private static String bookedRequestSubject = "CFT - Booking Details" ;
	private static String bookedRequestBodyEncoded = "%0A%0A%3Chtml%3E%3Chead%3E%0A%3Ctitle%3Ess%3C%2Ftitle%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Cstyle%3E%0A%20%20%20%20body%20%7B%0A%09%09font-family%3A%20%27Open%20Sans%27%2C%20sans-serif%3B%0A%09%09position%3A%20relative%3B%0A%09%09margin%3A0%3B%0A%09%09font-size%3A10pt%3B%0A%09%7D%0A%3C%2Fstyle%3E%0A%3C%2Fhead%3E%0A%20%3Cbody%3E%0A%09%3Cdiv%20class%3D%27wrapper%27%3E%0A%09%09%3Ctable%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20border%3D%270%27%20style%3D%27margin%3A%200%20auto%3B%27%3E%0A%09%09%09%3Ctbody%3E%0A%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%3Ctd%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3Ctable%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20width%3D%27100%25%27%20height%3D%27100%25%27%20bgcolor%3D%27rgb(238%2C238%2C238)%27%20style%3D%27background%3Argb(238%2C238%2C238)%3Bwidth%3A100%25!important%3Bheight%3A100%25%3Bmargin%3A0%3Bpadding%3A0%3B%26gt%3B%0A%09%09%09%09%09%09%09%26lt%3Btbody%26gt%3B%0A%09%09%09%09%09%09%09%09%26lt%3Btr%26gt%3B%0A%09%09%09%09%09%09%09%09%09%26lt%3Btd%20align%3D%27%20center%27%3D%27%27%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3Da9%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Ca%20href%3D%27http%3A%2F%2Fcarfortour.com%27%20target%3D%27_blank%27%3E%3Cimg%20src%3D%27http%3A%2F%2Fi1190.photobucket.com%2Falbums%2Fz457%2Fravirajt01%2Fem_hd_zpsvz4fdxiw.png%27%20border%3D%270%27%20alt%3D%27%27%2F%3E%3C%2Fa%3E%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20style%3D%27border-bottom%3A%201px%20solid%20rgb(236%2C236%2C236)%3Bmin-height%3A%20200px%3Bfont-size%3A1.2em%3B%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(20%2C65%2C114)%3Bmargin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09Dear%20User%2C%20%3Cbr%3E%3Cbr%3E%3Cbr%3E%20Below%20are%20booking%20details%20with%20CFT%20%3Cbr%2F%3E%3Cbr%2F%3E%0A%0A%0A%3Ctable%20style%3D%22width%3A100%25%20%3B%20%22%20border%3D%27.5%27%20cellspacing%3D%220%22%20cellpadding%3D%228%22%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EEmail%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%20%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%20%23email%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EContact%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%20%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%20%23contactNumber%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EPick-up%20Location%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23pickupLocation%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EDrop-off%20Location%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23dropOffLocation%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EPick-up%20Date%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23pickupDate%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EDrop-off%20Date%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23dropOffDate%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EVehicle%20details%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23vehicleDetails%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%3C%2Ftable%3E%0A%20%3Cbr%3E%3Cbr%3E%20Thank%20You%20!%0A%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(127%2C193%2C15)%3B%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(153%2C153%2C153)%3B%20%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%0A%09%09%09%09%09%09%0A%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%3C%2Ftbody%3E%09%09%09%0A%09%09%3C%2Ftable%3E%0A%09%3C%2Fdiv%3E%0A%20%0A%3C%2Fbody%3E%3C%2Fhtml%3E";
	
	

	private static String bookedRequestSubjectForAdminc= "Received New Request for - ";
	private static String bookedRequestBodyToAdminEncoded = "%0A%0A%3Chtml%3E%3Chead%3E%0A%3Ctitle%3Ess%3C%2Ftitle%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Cstyle%3E%0A%20%20%20%20body%20%7B%0A%09%09font-family%3A%20%27Open%20Sans%27%2C%20sans-serif%3B%0A%09%09position%3A%20relative%3B%0A%09%09margin%3A0%3B%0A%09%09font-size%3A10pt%3B%0A%09%7D%0A%3C%2Fstyle%3E%0A%3C%2Fhead%3E%0A%20%3Cbody%3E%0A%09%3Cdiv%20class%3D%27wrapper%27%3E%0A%09%09%3Ctable%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20border%3D%270%27%20style%3D%27margin%3A%200%20auto%3B%27%3E%0A%09%09%09%3Ctbody%3E%0A%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%3Ctd%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3Ctable%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20width%3D%27100%25%27%20height%3D%27100%25%27%20bgcolor%3D%27rgb(238%2C238%2C238)%27%20style%3D%27background%3Argb(238%2C238%2C238)%3Bwidth%3A100%25!important%3Bheight%3A100%25%3Bmargin%3A0%3Bpadding%3A0%3B%26gt%3B%0A%09%09%09%09%09%09%09%26lt%3Btbody%26gt%3B%0A%09%09%09%09%09%09%09%09%26lt%3Btr%26gt%3B%0A%09%09%09%09%09%09%09%09%09%26lt%3Btd%20align%3D%27%20center%27%3D%27%27%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3Da9%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Ca%20href%3D%27http%3A%2F%2Fcarfortour.com%27%20target%3D%27_blank%27%3E%3Cimg%20src%3D%27http%3A%2F%2Fi1190.photobucket.com%2Falbums%2Fz457%2Fravirajt01%2Fem_hd_zpsvz4fdxiw.png%27%20border%3D%270%27%20alt%3D%27%27%2F%3E%3C%2Fa%3E%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20style%3D%27border-bottom%3A%201px%20solid%20rgb(236%2C236%2C236)%3Bmin-height%3A%20200px%3Bfont-size%3A1.2em%3B%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(20%2C65%2C114)%3Bmargin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09Dear%20User%2C%20%3Cbr%3E%3Cbr%3E%3Cbr%3E%20Below%20are%20booking%20details%20with%20CFT%20%3Cbr%2F%3E%3Cbr%2F%3E%0A%0A%0A%3Ctable%20style%3D%22width%3A100%25%20%3B%20%22%20border%3D%27.5%27%20cellspacing%3D%220%22%20cellpadding%3D%228%22%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EEmail%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%20%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%20%23email%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EContact%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%20%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%20%23contactNumber%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EPick-up%20Location%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23pickupLocation%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EDrop-off%20Location%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23dropOffLocation%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EPick-up%20Date%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23pickupDate%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EDrop-off%20Date%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23dropOffDate%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%20%20%3Ctr%3E%0A%20%20%20%20%3Ctd%3EVehicle%20details%3C%2Ftd%3E%0A%20%20%20%20%3Ctd%3E%3CFONT%20COLOR%3D%22%23800517%22%3E%23vehicleDetails%3C%2FFONT%3E%3C%2Ftd%3E%0A%20%20%3C%2Ftr%3E%0A%3C%2Ftable%3E%0A%20%3Cbr%3E%3Cbr%3E%20Thank%20You%20!%0A%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(127%2C193%2C15)%3B%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(153%2C153%2C153)%3B%20%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%0A%09%09%09%09%09%09%0A%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%3C%2Ftbody%3E%09%09%09%0A%09%09%3C%2Ftable%3E%0A%09%3C%2Fdiv%3E%0A%20%0A%3C%2Fbody%3E%3C%2Fhtml%3E";


	private static String accountVerificationSubject= "Verify account at CarForTour";
	private static String accountVerificationBody = "%0A%0A%3Chtml%3E%3Chead%3E%0A%3Ctitle%3Ess%3C%2Ftitle%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fdcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Cstyle%3E%0A%20%20%20%20body%20%7B%0A%09%09font-family%3A%20%27Open%20Sans%27%2C%20sans-serif%3B%0A%09%09position%3A%20relative%3B%0A%09%09margin%3A0%3B%0A%09%09font-size%3A10pt%3B%0A%09%7D%0A%3C%2Fstyle%3E%0A%3C%2Fhead%3E%0A%20%3Cbody%3E%0A%09%3Cdiv%20class%3D%27wrapper%27%3E%0A%09%09%3Ctable%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20border%3D%270%27%20style%3D%27margin%3A%200%20auto%3B%27%3E%0A%09%09%09%3Ctbody%3E%0A%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%3Ctd%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3Ctable%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20width%3D%27100%25%27%20height%3D%27100%25%27%20bgcolor%3D%27rgb(238%2C238%2C238)%27%20style%3D%27background%3Argb(238%2C238%2C238)%3Bwidth%3A100%25!important%3Bheight%3A100%25%3Bmargin%3A0%3Bpadding%3A0%3B%26gt%3B%0A%09%09%09%09%09%09%09%26lt%3Btbody%26gt%3B%0A%09%09%09%09%09%09%09%09%26lt%3Btr%26gt%3B%0A%09%09%09%09%09%09%09%09%09%26lt%3Btd%20align%3D%27%20center%27%3D%27%27%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3Da9%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Ca%20href%3D%27http%3A%2F%2Fcarfortour.com%27%20target%3D%27_blank%27%3E%3Cimg%20src%3D%27http%3A%2F%2Fi1190.photobucket.com%2Falbums%2Fz457%2Fravirajt01%2Fem_hd_zpsvz4fdxiw.png%27%20border%3D%270%27%20alt%3D%27%27%2F%3E%3C%2Fa%3E%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20style%3D%27border-bottom%3A%201px%20solid%20rgb(236%2C236%2C236)%3Bmin-height%3A%20200px%3Bfont-size%3A1.2em%3B%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(20%2C65%2C114)%3Bmargin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09Dear%20%23name%2C%20%3Cbr%3E%3Cbr%3E%3Cbr%3E%20Your%20request%20for%20account%20creation%20received%20at%20CarForTour.%20%0A%0A%09%09%09%20%3Cbr%2F%3E%3Cbr%2F%3E%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09To%20verify%20your%20regisrtation%20please%20click%20below%20link.%3Cbr%3E%0A%09%23verificationLink%0A%09%0A%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%0A%09%0A%09If%20you%20are%20not%20registered%20with%20CarForTour%20please%20ignore%20this%20mail.%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%0A%0A%20Thank%20You%20!%0A%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(127%2C193%2C15)%3B%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(153%2C153%2C153)%3B%20%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%0A%09%09%09%09%09%09%0A%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%3C%2Ftbody%3E%09%09%09%0A%09%09%3C%2Ftable%3E%0A%09%3C%2Fdiv%3E%0A%20%0A%3C%2Fbody%3E%3C%2Fhtml%3E";

	private static String accountVerifiedSubject= "Verified registration at CarForTour";
	private static String accountVerifiedBody = "%0A%0A%3Chtml%3E%3Chead%3E%0A%3Ctitle%3Ess%3C%2Ftitle%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fdcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Cstyle%3E%0A%20%20%20%20body%20%7B%0A%09%09font-family%3A%20%27Open%20Sans%27%2C%20sans-serif%3B%0A%09%09position%3A%20relative%3B%0A%09%09margin%3A0%3B%0A%09%09font-size%3A10pt%3B%0A%09%7D%0A%3C%2Fstyle%3E%0A%3C%2Fhead%3E%0A%20%3Cbody%3E%0A%09%3Cdiv%20class%3D%27wrapper%27%3E%0A%09%09%3Ctable%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20border%3D%270%27%20style%3D%27margin%3A%200%20auto%3B%27%3E%0A%09%09%09%3Ctbody%3E%0A%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%3Ctd%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3Ctable%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20width%3D%27100%25%27%20height%3D%27100%25%27%20bgcolor%3D%27rgb(238%2C238%2C238)%27%20style%3D%27background%3Argb(238%2C238%2C238)%3Bwidth%3A100%25!important%3Bheight%3A100%25%3Bmargin%3A0%3Bpadding%3A0%3B%26gt%3B%0A%09%09%09%09%09%09%09%26lt%3Btbody%26gt%3B%0A%09%09%09%09%09%09%09%09%26lt%3Btr%26gt%3B%0A%09%09%09%09%09%09%09%09%09%26lt%3Btd%20align%3D%27%20center%27%3D%27%27%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3Da9%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Ca%20href%3D%27http%3A%2F%2Fcarfortour.com%27%20target%3D%27_blank%27%3E%3Cimg%20src%3D%27http%3A%2F%2Fi1190.photobucket.com%2Falbums%2Fz457%2Fravirajt01%2Fem_hd_zpsvz4fdxiw.png%27%20border%3D%270%27%20alt%3D%27%27%2F%3E%3C%2Fa%3E%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20style%3D%27border-bottom%3A%201px%20solid%20rgb(236%2C236%2C236)%3Bmin-height%3A%20200px%3Bfont-size%3A1.2em%3B%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(20%2C65%2C114)%3Bmargin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09Dear%20%23name%2C%20%3Cbr%2F%3E%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0AYour%20registration%20is%20verified%20successfully%20at%20CarForTour.%3Cbr%2F%3E%20Below%20are%20the%20registration%20details%20-%20%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0AEmail%20%3A%20%23email%20%3Cbr%3E%0APassword%20%3A%20%23password%20%3Cbr%3E%0AContact%20%3A%20%23contactNumber%0A%0A%09%09%09%20%3Cbr%2F%3E%3Cbr%2F%3E%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%09%0A%0A%20Thank%20You%20!%0A%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(127%2C193%2C15)%3B%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(153%2C153%2C153)%3B%20%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%0A%09%09%09%09%09%09%0A%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%3C%2Ftbody%3E%09%09%09%0A%09%09%3C%2Ftable%3E%0A%09%3C%2Fdiv%3E%0A%20%0A%3C%2Fbody%3E%3C%2Fhtml%3E";

	private static String forgotpassSubject= "Forgot Password at CarForTour";
	private static String forgotpassBody = "%0A%0A%3Chtml%3E%3Chead%3E%0A%3Ctitle%3Ess%3C%2Ftitle%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fdcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Clink%20href%3D%27http%3A%2F%2Ffonts.googleapis.com%2Fcss%3Ffamily%3DOpen%2BSans%27%20rel%3D%27stylesheet%27%20type%3D%27text%2Fcss%27%3E%0A%3Cstyle%3E%0A%20%20%20%20body%20%7B%0A%09%09font-family%3A%20%27Open%20Sans%27%2C%20sans-serif%3B%0A%09%09position%3A%20relative%3B%0A%09%09margin%3A0%3B%0A%09%09font-size%3A10pt%3B%0A%09%7D%0A%3C%2Fstyle%3E%0A%3C%2Fhead%3E%0A%20%3Cbody%3E%0A%09%3Cdiv%20class%3D%27wrapper%27%3E%0A%09%09%3Ctable%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20border%3D%270%27%20style%3D%27margin%3A%200%20auto%3B%27%3E%0A%09%09%09%3Ctbody%3E%0A%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%3Ctd%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3Ctable%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20width%3D%27100%25%27%20height%3D%27100%25%27%20bgcolor%3D%27rgb(238%2C238%2C238)%27%20style%3D%27background%3Argb(238%2C238%2C238)%3Bwidth%3A100%25!important%3Bheight%3A100%25%3Bmargin%3A0%3Bpadding%3A0%3B%26gt%3B%0A%09%09%09%09%09%09%09%26lt%3Btbody%26gt%3B%0A%09%09%09%09%09%09%09%09%26lt%3Btr%26gt%3B%0A%09%09%09%09%09%09%09%09%09%26lt%3Btd%20align%3D%27%20center%27%3D%27%27%20valign%3D%27top%27%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3Da9%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Ca%20href%3D%27http%3A%2F%2Fcarfortour.com%27%20target%3D%27_blank%27%3E%3Cimg%20src%3D%27http%3A%2F%2Fi1190.photobucket.com%2Falbums%2Fz457%2Fravirajt01%2Fem_hd_zpsvz4fdxiw.png%27%20border%3D%270%27%20alt%3D%27%27%2F%3E%3C%2Fa%3E%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%3Ctable%20width%3D%27640%27%20border%3D%270%27%20cellspacing%3D%270%27%20cellpadding%3D%270%27%20style%3D%27border-bottom%3A%201px%20solid%20rgb(236%2C236%2C236)%3Bmin-height%3A%20200px%3Bfont-size%3A1.2em%3B%27%3E%0A%09%09%09%09%09%09%09%3Ctbody%3E%0A%09%09%09%09%09%09%09%09%3Ctr%3E%0A%09%09%09%09%09%09%09%09%09%3Ctd%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(20%2C65%2C114)%3Bmargin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09Dear%20%23name%2C%20%3Cbr%2F%3E%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0AYour%20password%20and%20account%20details%20at%20CarForTour%20is%20as%20below.%3Cbr%2F%3E%20%0A%09%09%09%09%09%09%09%09%09%09%09%0AEmail%20%3A%20%23email%20%3Cbr%3E%0APassword%20%3A%20%23password%20%3Cbr%3E%0AContact%20%3A%20%23contactNumber%0A%0A%09%09%09%20%3Cbr%2F%3E%3Cbr%2F%3E%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%09%0A%0A%20Thank%20You%20!%0A%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(127%2C193%2C15)%3B%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3Cspan%20style%3D%27color%3Argb(153%2C153%2C153)%3B%20%20margin%3A0%201%25%3Bdisplay%3Ablock%3B%27%3E%0A%09%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%3Cbr%2F%3E%0A%09%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%09%3C%2Fspan%3E%0A%09%09%09%09%09%09%09%09%09%09%3Cbr%2F%3E%09%09%09%09%09%09%09%09%09%09%0A%09%09%09%09%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%09%09%09%09%3C%2Ftbody%3E%0A%09%09%09%09%09%09%3C%2Ftable%3E%0A%09%09%09%09%09%09%0A%09%09%09%09%09%09%0A%09%09%09%09%09%3C%2Ftd%3E%0A%09%09%09%09%3C%2Ftr%3E%0A%09%09%09%3C%2Ftbody%3E%09%09%09%0A%09%09%3C%2Ftable%3E%0A%09%3C%2Fdiv%3E%0A%20%0A%3C%2Fbody%3E%3C%2Fhtml%3E%22%3B%0A";
	
	//@Override
	public void suscribe(String email) {
		main(email);
	}
	
	//@Override
	
	
	public void sendMail(MailType mailType ,Object object) {
		
		MailDetails mailDetails = new MailDetails();
		
		setMessageBody(mailType,object,mailDetails);
		sendMail(mailDetails.getToEmail() , fromEmail, fromPW,mailDetails.getSubject(),mailDetails.getBody());
		
		
	}
	
	
	public void sendTourBookMail(BookTour bookTour) {

		String body= null;
		String bodyToAdmin= null;
		try {
			body = URLDecoder.decode(bookedRequestBodyEncoded, "UTF-8");
			bodyToAdmin = URLDecoder.decode(bookedRequestBodyToAdminEncoded, "UTF-8");

			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	
		
		//sendBookedMailToAdmin
		//setMessageBody(MailType.TOUR_BOOK_ADMIN,bodyToAdmin,bookTour);
				sendMail(fromEmail, fromEmail, fromPW,bookedRequestSubjectForAdminc + bookTour.getEmail() ,bodyToAdmin );

		//sendBookedMailToCust
		//setMessageBody(MailType.TOUR_BOOK,body,bookTour);
				
				
					sendMail(bookTour.getEmail() , fromEmail, fromPW,bookedRequestSubject,body);
		
		

		
/*
		 EmployeeDao dao=(EmployeeDao)InitialLoader.ctx.getBean("edao");
		    int status= employeeDao.saveEmployee(new Employee(102,"Amit",35000));  
		    System.out.println(status);*/  
		/*
			Employee employee = new Employee();
			employee.setId(22);
			employee.setName("abc");
			employee.setSalary(100000);
			
		    employeeDao.saveEmployee(employee);  
		    System.out.println("saved..");*/
		    
	}
	
	
	public static void main(String email) {

		//URLDecoder ud = new java.net.URLDecoder();
		String suscribtionMailBody = null;
		try {
			suscribtionMailBody = URLDecoder.decode(suscribtionBodyEncoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		sendMail(email, fromEmail, fromPW,suscribtionSubject,suscribtionMailBody);
		
	}
	
	private static void sendMail(String sendTo, final String fromUser, final String frompassword,String subject , String body) {
		//Get the session object  
		Properties props = new Properties();  
	/*	props.put("mail.smtp.host", "smtp.gmail.com");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class",  
				"javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");*/ 
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		System.out.println("conf set...");

		//Session session = Session.getDefaultInstance(props,  
		Session session = Session.getInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(fromUser,frompassword); 
			}  
		});  

		//compose message  
		try {  
			
			
			
			
			System.out.println("session:"+session);
			MimeMessage message = new MimeMessage(session);  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
			message.setSubject(subject);  
			//message.setText(body);  
			message.setContent(body,"text/html");

			//send message  
			Transport.send(message);  

			//System.out.println("message sent successfully");  

		} catch (MessagingException e) {throw new RuntimeException(e);}  


	}

	private static void setMessageBody(MailType mailType,Object bodyObject, MailDetails mailDetails) {

		BookTour bookTour =null;
		User user =null;
		Vender vender=null;
		
		String body = null;
		switch (mailType) {
		
		case VRIFY_REG :

			user = (User) bodyObject;
			
			StringBuilder link = new StringBuilder();
			mailDetails.setToEmail(user.getEmail());
			mailDetails.setSubject(accountVerificationSubject);
			
			try {
				body = URLDecoder.decode(accountVerificationBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			body = body.replace("#name", user.getName());
			body = body.replace("#email", user.getEmail());
			body = body.replace("#contactNumber", user.getContactNumber());
			link.append(domainName).append(userVerifyRegLink).append(user.getOtp());
			body = body.replace("#verificationLink",link );
			
			
			break;
			
		case VRIFYED_REG :

			user = (User) bodyObject;
			
			mailDetails.setToEmail(user.getEmail());
			mailDetails.setSubject(accountVerifiedSubject);
			
			try {
				
				
				
				body = URLDecoder.decode(accountVerifiedBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			body = body.replace("#name", user.getName());
			body = body.replace("#email", user.getEmail());
			body = body.replace("#password", user.getPassword());
			body = body.replace("#contactNumber", user.getContactNumber());
			
			
			break;
		
		case TOUR_BOOK :
			
			bookTour = (BookTour) bodyObject;
			
			mailDetails.setToEmail( bookTour.getEmail());
			mailDetails.setSubject( bookedRequestSubject);
			
			try {
				body = URLDecoder.decode(bookedRequestBodyEncoded, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			
			body = body.replace("#email", bookTour.getEmail());
			body = body.replace("#contactNumber", bookTour.getContactNumber());
			body = body.replace("#pickupLocation", bookTour.getFromLocatiom());
			body = body.replace("#dropOffLocation", bookTour.getToLocation());
			body = body.replace("#pickupDate", bookTour.getFromDate()+ " " + bookTour.getFromTime());
			//body = body.replace("#dropOffDate", bookTour.getToDate());
			if(bookTour.getVehicleDetails()!= null)
			body = body.replace("#vehicleDetails", bookTour.getVehicleDetails());	
			
			break;
			
			
		case TOUR_BOOK_ADMIN :
			
			bookTour = (BookTour) bodyObject;
			
			mailDetails.setToEmail(fromEmail);
			mailDetails.setSubject( bookedRequestSubjectForAdminc.concat(bookTour.getEmail()));
			
			try {
				body = URLDecoder.decode(bookedRequestBodyToAdminEncoded, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			body = body.replace("#email", bookTour.getEmail());
			body = body.replace("#contactNumber", bookTour.getContactNumber());
			body = body.replace("#pickupLocation", bookTour.getFromLocatiom());
			body = body.replace("#dropOffLocation", bookTour.getToLocation());
			body = body.replace("#pickupDate", bookTour.getFromDate()+ " " + bookTour.getFromTime());
			//body = body.replace("#dropOffDate", bookTour.getToDate());
			if(bookTour.getVehicleDetails()!= null)
			body = body.replace("#vehicleDetails", bookTour.getVehicleDetails());	
			
			
			break;
			
		case FORGOT_PASS :

			user = (User) bodyObject;
			
			mailDetails.setToEmail(user.getEmail());
			mailDetails.setSubject(forgotpassSubject);
			
			try {
				
				
				
				body = URLDecoder.decode(forgotpassBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			body = body.replace("#name", user.getName());
			body = body.replace("#email", user.getEmail());
			body = body.replace("#password", user.getPassword());
			body = body.replace("#contactNumber", user.getContactNumber());
			
			
			break;

			
		case VVRIFY_REG :

			vender = (Vender) bodyObject;
			
			StringBuilder vlink = new StringBuilder();
			mailDetails.setToEmail(vender.getEmail());
			mailDetails.setSubject(accountVerificationSubject);
			
			try {
				body = URLDecoder.decode(accountVerificationBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			body = body.replace("#name", vender.getName());
			body = body.replace("#email", vender.getEmail());
			body = body.replace("#contactNumber", vender.getContactNumber());
			vlink.append(domainName).append(venderVerifyRegLink).append(vender.getOtp());
			body = body.replace("#verificationLink",vlink );
			
			
			break;
		case VVRIFYED_REG :

			vender = (Vender) bodyObject;
			
			mailDetails.setToEmail(vender.getEmail());
			mailDetails.setSubject(accountVerifiedSubject);
			
			try {
				
				
				
				body = URLDecoder.decode(accountVerifiedBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			body = body.replace("#name", vender.getName());
			body = body.replace("#email", vender.getEmail());
			body = body.replace("#password", vender.getPassword());
			body = body.replace("#contactNumber", vender.getContactNumber());
			
			
			break;
			
		default:
			break;
		}
		
		mailDetails.setBody(body);
		
		//System.out.println("body"+body  );
		
	}

	private static void sendMail(String sendTo, final String fromUser, final String frompassword,String subject , String body,int a) {
		//Get the session object  
		Properties props = new Properties();  
	/*	props.put("mail.smtp.host", "smtp.gmail.com");  
		props.put("mail.smtp.socketFactory.port", "465");  
		props.put("mail.smtp.socketFactory.class",  
				"javax.net.ssl.SSLSocketFactory");  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");*/ 
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		System.out.println("conf set...");

		//Session session = Session.getDefaultInstance(props,  
		Session session = Session.getInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(fromUser,frompassword); 
			}  
		});  

		//compose message  
		try {  
			
			System.out.println("session:"+session);
			MimeMessage message = new MimeMessage(session);  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
			message.setSubject(subject);  
			//message.setText(body);  
			message.setContent(body,"text/html");

			//send message  
			Transport.send(message);  

			System.out.println("message sent successfully");  

		} catch (MessagingException e) {throw new RuntimeException(e);}  


	}
	
}
