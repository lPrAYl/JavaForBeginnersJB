class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code here
        StringBuilder res = new StringBuilder();

        for (char i = 'A'; i <= 'Z'; i++) {
            res.append(i);

            if (i != 'Z') {
                res.append(" ");
            }
        }

        return res;
    }
}