
class ArrayOperations {
    public static int[][][] createCube() {
        // write your code here
        int[][][] array = new int[3][3][3];
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++, count++) {
                    array[i][j][k] = count;
                }
            }
        }
        return array;
    }
    
    public static void main(String[] argc) {
        System.out.println(Arrays.deepToString(createCube()));
    }
}
