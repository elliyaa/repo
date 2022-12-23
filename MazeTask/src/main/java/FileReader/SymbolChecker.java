package FileReader;

import Exceptions.MultipleStartingPointsException;
import Exceptions.NoStartingPointException;
import Exceptions.UnknownSymbolException;

@FunctionalInterface
public interface SymbolChecker {
	
	public void checkSymbol() throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException;
	
}
