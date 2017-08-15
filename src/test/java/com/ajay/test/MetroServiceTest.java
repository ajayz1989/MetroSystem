package com.ajay.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ajay.card.SmartCard;
import com.ajay.dao.SmartCardDAO;
import com.ajay.exception.NoMinBalance;
import com.ajay.service.MetroService;

@RunWith(SpringRunner.class)
public class MetroServiceTest {

	@InjectMocks
	private MetroService metroService;
	
	@Mock
	private SmartCardDAO smartCardDAO;
	
	@MockBean
	private Optional<SmartCard> osmartCard;
	
	private SmartCard smartCard;
	
	@Before
	public void setUp() {
		smartCard = new SmartCard("ajay",44);
//		metroService = new MetroService();
		Mockito.when(osmartCard.get()).thenReturn(smartCard);
	}
	
	@Test
	public void testSwipeOut() throws NoMinBalance {
		SmartCard sm = metroService.executeSwipeOut(1L, "A6", true);
		Assert.assertTrue(sm.getBalance() == 44);
	}
}
