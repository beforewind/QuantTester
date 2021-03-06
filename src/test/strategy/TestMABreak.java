package test.strategy;

import java.time.LocalDateTime;

import data.TIME_FRAME;
import helper.DateTimeHelper;
import indicator.APPLIED_PRICE;
import indicator.MA;
import strategy.MABreakStrategy;
import tester.AbstractStrategyTester;

public class TestMABreak {

	public static void main(String[] args) {
		Integer Period = 80;
		AbstractStrategyTester st = new tester.RealStrategyTester("cu", TIME_FRAME.DAY);
		LocalDateTime start_date = LocalDateTime.of(2010, 1, 1,  0, 0, 0);
		LocalDateTime   end_date = LocalDateTime.of(2016, 1, 1, 17, 0, 0);
		st.setTestDateRange((int) DateTimeHelper.Ldt2Long(start_date), (int) DateTimeHelper.Ldt2Long(end_date));
		st.setStrategyParam(MABreakStrategy.class, Period, MA.MODE_EMA, APPLIED_PRICE.PRICE_CLOSE);
		st.evaluate();
		st.getPerformances().printAll();
		st.drawDailyBalance(MABreakStrategy.class.getSimpleName() + ".png");
	}
}
