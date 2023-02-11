interface name {

        default void Getname(String g) {
            String gg=g.replaceAll("class ","");
            System.out.println(gg);
        }
    }


