package lw01.prelab;

public class MonoPrint extends PrintJob {
    private static final int COST_PER_PAGE = 500;
    public MonoPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCost() {
        return getPages() * COST_PER_PAGE;
    }

    @Override
    public String summary() {
        return "[MONO] " + super.summary();
    }
}