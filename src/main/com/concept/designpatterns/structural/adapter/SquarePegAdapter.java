package src.main.com.concept.designpatterns.structural.adapter;

/**
 * Adapter to convert square peg to round holes
 */
public class SquarePegAdapter extends RoundPeg {
    //holds instance of service class
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    /**
     * implement methods of client class
     * @return
     */
    @Override
    public double getRadius() {
        double result;
        // Calculate a minimum circle radius, which can fit this peg.
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }
}
