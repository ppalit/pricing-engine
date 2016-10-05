/**
 * 
 */
package dev.zero.pricingengine.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author ppalit
 *
 */
public class PricingRulesTest {

	/**
	 * Test method for {@link dev.zero.pricingengine.rules.PricingRules#markup(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testMarkup() {
		PricingRules rules = new PricingRules();
		assertEquals(0, rules.markup("H", "H"));
		assertEquals(-5, rules.markup("H", "L"));
		assertEquals(5, rules.markup("L", "H"));
		assertEquals(10, rules.markup("L", "L"));
	}

}
