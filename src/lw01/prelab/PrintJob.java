package lw01.prelab;

public abstract class PrintJob {
    private String id;
    private int pages;

    public PrintJob(String id, int pages) {
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public abstract int calculateCost();

    public String summary() {
        return "ID: " + id + " | Pages: " + pages + " | Cost: Rp" + calculateCost();
    }
}