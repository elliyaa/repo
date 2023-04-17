package FileReader;

@FunctionalInterface
public interface SymbolChecker {
	
	public void checkSymbol(int line, int column);
}
