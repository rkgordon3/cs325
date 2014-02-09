package inclass.frs.hotgammon.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
		TestAlphamon.class,  
		SixMoveWinTests.class, 
		TestBoard.class, 
		BearOffWinnerTests.class,
		SimpleMoveValidatorTests.class,
		TestLocation.class } )
public class AllTests {
}
