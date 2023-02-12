 class ShapeName {


     public void printShapeName(Shape shape) {
String name= String.valueOf(shape.getClass());
String name2=name.replaceAll("class ","");
        System.out.println(name2);
    }
}
