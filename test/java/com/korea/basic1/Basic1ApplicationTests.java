package com.korea.basic1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Basic1ApplicationTests {

	@Autowired
	private BookstoreRepository bookstoreRepository;

	@Autowired
	private BookRepository bookRepository;

// 서점 두개를 만들어주세요.

// 서점은 각각 이름과 보유서적으로 구성됩니다.

// 각각 코리아서점, 아이티문고

	@Test
	void t2() {
		Bookstore bs1 = new Bookstore();
		bs1.setName("코리아서점");
		this.bookstoreRepository.save(bs1);

		Bookstore bs2 = new Bookstore();
		bs2.setName("아이티문고");
		this.bookstoreRepository.save(bs2);

// 구현 및 테스트

	}



// 모든 서점 정보 조회

// 출력 : 코리아서점, 아이티문고

	@Test

	void t3() {

		List<Bookstore> all = this.bookstoreRepository.findAll();
		assertEquals(2, all.size());

		Bookstore bs = all.get(0);
		assertEquals("코리아서점", bs.getName());

// 구현 및 테스트
	}





// 아이티문고의 이름을 IT문고로 변경해주세요.
// 변경 후엔 t3() 메서드를 실행해 확인해주세요.
// 출력 : 코리아서점, IT문고

	@Test

	void t4() {

		Optional<Bookstore> ob = this.bookstoreRepository.findById(2);
		assertTrue(ob.isPresent());
		Bookstore bs = ob.get();
		bs.setName("IT문고");
		this.bookstoreRepository.save(bs);
		}

// 구현 및 테스트




// 책 5권을 만들어주세요.
// 책은 제목과 저자, 보유 서점으로 구성됩니다.
// 책은 각각
// 어린왕자, 생떽쥐페리, 코리아서점
// 해리포터, 조앤 롤링, 코리아서점
// 죄와벌, 도스토예프스키, 코리아서점
// 점프 투 스프링부트, 박응용, 아이티문고
// 수학의 정석, 홍성대, 아이티문고
// 로 만들어주세요.


//Bookstore bs1 = new Bookstore();
//		bs1.setName("코리아서점");
//		this.bookstoreRepository.save(bs1);
	@Test
	void t5() {


		Optional<Bookstore> ob = this.bookstoreRepository.findById(1);
		assertTrue(ob.isPresent());
		Bookstore bs1 = ob.get();

		Optional<Bookstore> ob2 = this.bookstoreRepository.findById(2);
		assertTrue(ob2.isPresent());
		Bookstore bs2 = ob2.get();

		Book b = new Book();
		b.setSubject("어린왕자");
		b.setWriter("생떽쥐베리");
		b.setBookstore(bs1);
		this.bookRepository.save(b);

		Book b1 = new Book();
		b1.setSubject("해리포터");
		b1.setWriter("조앤 롤링");
		b1.setBookstore(bs1);
		this.bookRepository.save(b1);

		Book b2 = new Book();
		b2.setSubject("죄와벌");
		b2.setWriter("도스토예프스키");
		b2.setBookstore(bs1);
		this.bookRepository.save(b2);

		Book b3 = new Book();
		b3.setSubject("점프 투 스프링부트");
		b3.setWriter("박응용");
		b3.setBookstore(bs2);
		this.bookRepository.save(b3);

		Book b4 = new Book();
		b4.setSubject("수학의 정석");
		b4.setWriter("홍성대");
		b4.setBookstore(bs2);
		this.bookRepository.save(b4);

// 구현 및 테스트

	}



// 모든 책의 제목을 출력해주세요.

	@Test

	void t6() {

		List<Book> all = this.bookRepository.findAll();

		System.out.println(all.get(0).getSubject());
		System.out.println(all.get(1).getSubject());
		System.out.println(all.get(2).getSubject());
		System.out.println(all.get(3).getSubject());
		System.out.println(all.get(4).getSubject());


// 구현 및 테스트

	}



// 코리아서점에서 판매하는 책의 제목을 출력해주세요.

	@Test

	void t7() {

		List<Book> b = this.bookRepository.findAll();
		for (Book book : b) {
			if(book.getBookstore().getId() == 1){
				System.out.println(book.getSubject());
			}
		}
	}


// 구현 및 테스트





// 박응용이 쓴 책을 삭제해주세요.

// 삭제 후 t5 메서드를 이용해 확인해주세요.

	@Test

	void t8() {
		List<Book> books = bookRepository.findAll();
		Bookstore koreaBookstore = bookstoreRepository.findById(2).orElse(null);

		if (koreaBookstore != null) {
			List<Book> booksToDelete = new ArrayList<>();

			for (Book book : books) {
				if (book.getBookstore().equals(koreaBookstore) && book.getWriter().equals("박응용")) {
					booksToDelete.add(book);
				}
			}

			for (Book book : booksToDelete) {
				bookRepository.delete(book);
			}

			List<Book> remainingBooks = bookRepository.findAll();
			for (Book book : remainingBooks) {
				System.out.println(book.getSubject());
			}
		}
	}
// 구현 및 테스트



}
