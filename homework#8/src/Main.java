public class Main {

    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape kite = new Kite();
        Shape parallelogram = new Parallelogram();
        Shape rhombus = new Rhombus();
        Shape square = new Square();

        Printer printer = new Printer();
        printer.printShapeName(circle);
        printer.printShapeName(kite);
        printer.printShapeName(parallelogram);
        printer.printShapeName(rhombus);
        printer.printShapeName(square);
    }
}





