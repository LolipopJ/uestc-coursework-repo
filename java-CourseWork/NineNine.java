package coursework;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/24
 */
public class NineNine {
    public static void main(String[] args){
        for(int i=1; i<=9; i++){
            for(int n=1; n<=9; n++){
                System.out.printf("%d*%d得%d\t", i, n, i*n);
            }
            System.out.print("\n");
        }
    }
}
