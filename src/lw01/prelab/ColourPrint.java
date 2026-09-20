package lw01.prelab;

public class ColourPrint extends PrintJob {
    private static final int COST_PER_PAGE = 1000; 

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCost() {
        return getPages() * COST_PER_PAGE;
    }

    @Override
    public String summary() {
        return "[COLOUR] " + super.summary();
    }
}