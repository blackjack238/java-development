public class Shape {

    public String getName() {
        return String.valueOf(getClass()).replaceAll("class ","");
    };
}
