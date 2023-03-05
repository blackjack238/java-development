package task1;

import java.util.HashMap;
import java.util.List;

public class DataFilter {
    public List Filter(List numbers){

        for (int i=0;i< numbers.size();){
            int f=0;
            int check=0;

            String Number= String.valueOf(numbers.get(i));

            for (int j=0;j<Number.length();j++) {
                char x = Number.charAt(j);



                if (j == 0) {
                    if (!Character.isDigit(x) && x != '(') {
                        numbers.remove(i);
                        f++;
                        break;
                    }
                }
                if (j == 0) {
                    if (Character.isDigit(x)) {
                        check++;
                    }
                }
                if (check == 1) {

                    if (j == 3 || j == 7) {

                        if (x != '-') {
                            numbers.remove(i);
                           f++;
                            break;

                        }
                    }
                }
                if (j == 0) {
                    if (x == '(') {
                        check = 2;
                    }
                }
                if (check == 2) {
                    if (j == 4) {
                        if (x != ')') {
                            numbers.remove(i);
                            f++;
                            break;
                        }
                    }
                    if (j == 9) {
                        if (x != '-') {
                            numbers.remove(i);
                                f++;
                            break;
                        }
                    }
                }

            }
if (f==0)i++;
            }
    return numbers;}
}
