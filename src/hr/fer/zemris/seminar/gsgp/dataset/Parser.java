package hr.fer.zemris.seminar.gsgp.dataset;

/**
 * Created by zac on 26.04.17.
 */

public class Parser {

//    public static List<int[]> parse(String path) {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        List<int[]> dataset = new ArrayList<>();
//
//        try {
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                line = line.trim();
//                //System.out.println(line);
//                if (!line.isEmpty() && !line.startsWith("#")) {
//                    String[] xx = line.split("\\s+");
//                    int[] value = new int[xx.length];
//                    for (int i = 0; i < value.length; i++) {
//                        value[i] = Integer.parseInt(xx[i]);
//                    }
//                    dataset.add(value);
//                }
//            }
//
//            reader.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return dataset;
//    }

    public static void main(String[] args) {
        BooleanDataset dataset = new BooleanDataset("data/f1.txt");
        for (BooleanSample sample : dataset) {
            System.out.println(sample.toString());
        }
    }

}