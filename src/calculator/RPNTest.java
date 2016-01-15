package calculator;

import static org.hamcrest.number.IsCloseTo.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RPNTest {

	@Test
	public void 計算テスト(){

		assertThat(RPN.calc("1 + 2").doubleValue(), closeTo(3.0, 0.01));
		assertThat(RPN.calc("1 + 2 * 3").doubleValue(), closeTo(7.0, 0.01));
		assertThat(RPN.calc("( 1 + 2 ) * 3").doubleValue(), closeTo(9.0, 0.01));
		assertThat(RPN.calc("( 1 + 2 ) ^ 3").doubleValue(), closeTo(27.0, 0.01));

	}

}