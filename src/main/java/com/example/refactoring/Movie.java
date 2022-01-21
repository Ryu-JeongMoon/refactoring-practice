package com.example.refactoring;

public class Movie {

  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;
  public static final int CHILDREN = 2;

  private String _title;
  private int _priceCode;
  private Price _price;

  public Movie(String _title, int _priceCode) {
    this._title = _title;
    set_priceCode(_priceCode);
  }

  public int get_priceCode() {
    return _price.getPriceCode();
  }

  public void set_priceCode(int arg) {
    switch (arg) {
      case REGULAR:
        _price = new RegularPrice();
        break;
      case CHILDREN:
        _price = new ChildrenPrice();
        break;
      case NEW_RELEASE:
        _price = new NewReleasePrice();
        break;
      default:
        throw new IllegalArgumentException("가격 코드가 잘못됐습니다");
    }
  }

  public String get_title() {
    return _title;
  }

  public double getCharge(int daysRented) {
    return _price.getCharge(daysRented);
  }

  public int getFrequentRenterPoints(int daysRented) {
    if (get_priceCode() == Movie.NEW_RELEASE && daysRented > 1) {
      return 2;
    } else {
      return 1;
    }
  }
}

/*
getMovie().getPriceCode() 와 같은 train wreck 피하기 위해 메서드 이동
Movie 에 존재하지 않는 daysRented 를 parameter 로 넘겨줌

Movie, Rental 정보를 사용하는데 왜 Movie 로 이동한 것인가
사용자의 잠재적인 요청이 비디오 종류를 변경하는 것이기 때문
 */