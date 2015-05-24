package dfEditor.tests;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.JunitReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;

import dfEditor.IntegerTextField;

@UseReporter(JunitReporter.class)
public class IntegerTextFieldTest
{
  @Test
  public void test()
  {
    String[] inputs = {"0", " 1", "500", "-1", "- 1", "2.3", "500000", "2,000", "2.000", "4e5", "df"};
    IntegerTextField integerTextField = new IntegerTextField().withMinimum(0).withMaximum(1000).withDefault(2);
    Approvals.verifyAll(integerTextField.toString(), inputs,
        n -> String.format("%s -> %s", n, integerTextField.getNumber(n)));
  }
}
