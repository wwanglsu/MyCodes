package wang.c10;

public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Cache cache = new Cache();
        for (int i = 0; i < 20; i++) {
            String query = "query" + i;
            cache.set(query, generateResults(i));
            if (i == 9 || i == 16 || i == 19) {
                cache.getResults("query" + 2);
                cache.getResults("query" + 6);
                cache.getResults("query" + 9);
            }
        }

        for (int i = 0; i < 30; i++) {
            String query = "query" + i;
            String[] results = cache.getResults(query);
            System.out.print(query + ": ");
            if (results == null) {
                System.out.print("null");
            } else {
                for (String s : results) {
                    System.out.print(s + ", ");
                }
            }
            System.out.println("");
        }
    }

    static String[] generateResults(int i){
        String[] results={"result A "+i,"result B "+i,"result C "+i};
        return results;
    }

}
