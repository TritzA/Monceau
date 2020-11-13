package tp4;

public class Point implements Comparable<Point> {
    // TODO vous pouvez modifier ce que vous voulez, tant que vous ne modifiez pas les tests

    private final Integer x;
    private final Integer y;
    private Integer index;

    private boolean connu;
    private boolean mauvais;

    public Point(String xy) {
        String[] xAndY = xy.split(" +");
        this.x = Integer.parseInt(xAndY[0]);
        this.y = Integer.parseInt(xAndY[1]);
        this.connu = false;
        this.mauvais = false;
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.connu = false;
        this.mauvais = false;
    }

    @Override
    public String toString() {
        return String.format("{X: %d, Y: %d}", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }

        Point point = (Point) obj;
        return point.x.equals(x) && point.y.equals(y);
    }

    @Override
    public int compareTo(Point point) {
        // TODO ceci vous sera peut etre utile
        return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isConnu() {
        return connu;
    }

    public void setConnu(boolean connu) {
        this.connu = connu;
    }

    public boolean isMauvais() {
        return mauvais;
    }


    public void setMauvais(boolean mauvais) {
        this.mauvais = mauvais;
    }
}
