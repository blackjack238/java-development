public class ShapeName {
    public void printShapeName(String shape) {
        String reworkedShapeName=shape.replaceAll("class ","");
        System.out.println(reworkedShapeName);
    }
}
