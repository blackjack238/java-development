
    class Shape {

private Shape shape;

        public Shape getShape() {
            shape=new Shape();
            return shape;
        }

        protected void printShape(){


            new ShapeName().printShapeName(getShape());

        }
}

