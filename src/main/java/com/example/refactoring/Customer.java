package com.example.refactoring;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

  private String _name;
  private Vector _rentals = new Vector();

  public Customer(String _name) {
    this._name = _name;
  }

  public void addRental(Rental arg) {
    _rentals.addElement(arg);
  }

  public String getName() {
    return _name;
  }

  public String htmlStatement() {
    Enumeration rentals = _rentals.elements();
    String result = "<H1><EM>" + getName() + " 고객님의 대여 기록</EM></H1><P>\n";
    while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();

      result += each.get_movie().get_title() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
    }
    result += "<P>누적 대여료: <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
    result += "적립 포인트: <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM><P>";
    return result;
  }

  public String statement() {
    Enumeration rentals = _rentals.elements();
    String result = getName() + " 고객님의 대여 기록\n";
    while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();

      result += "\t" + each.get_movie().get_title() + "\t" + String.valueOf(each.getCharge()) + "\n";
    }

    result += "누적 대여료: " + String.valueOf(getTotalCharge()) + "\n";
    result += "적립 포인트: " + String.valueOf(getTotalFrequentRenterPoints());
    return result;
  }

  private int getTotalFrequentRenterPoints() {
    int result = 0;
    Enumeration rentals = _rentals.elements();
    while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();
      result += each.getFrequentRenterPoints();
    }
    return result;
  }

  private double getTotalCharge() {
    double result = 0;
    Enumeration rentals = _rentals.elements();
    while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();
      result += each.getCharge();
    }
    return result;
  }
}

/*
기존의 amountFor 메서드는 Rental Class property 만 이용하므로 Rental 로 이동시킨다
이때 amountFor 가 public 이고 다른 클래스의 인터페이스를 변경할 수 없는 경우에는 단순히 위임만 하도록 만들기도 한다

double thisAmount = 0;
thisAmount 는 each.getCharge() 의 결과를 담기 위해 존재한다
긴 메서드 안에서 임시 변수가 늘어날수록 성능은 저하된다?!
성능 최적화를 위해 가능한 한 임시 변수를 줄이고 메서드 호출로 대체한다
 */