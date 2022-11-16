import java.util.*;
public class Main {


    static class Order {
        private int items = 0;
        private boolean bonus = false;
        private float finalPrice = 0;
        private int finalQuantity = 0;

        //        the contents object is a list of arrays.
//        This each array holds a unique combination of Size, Matte, Quantity, and Rush
        private ArrayList<Object[]> contents = new ArrayList<Object[]>();

//        addSeries adds an array to the contents list.
//        Alternativly, it can add to an existing array if the Size, Matte and Rush are the same.
        public void addSeries(String Size, boolean Matte, int Quantity, boolean Rush) {
            boolean homogeneous = false;
            if (items != 0) {
                for (int i = 0; i < this.items; i++) {
                    if ((Size.equals((String) this.contents.get(i)[0])) && (Matte == (boolean) this.contents.get(i)[1]) && (Rush == (boolean) this.contents.get(i)[3])) {
                        Object[] toChange = {(String) this.contents.get(i)[0], (boolean) this.contents.get(i)[1], (int) this.contents.get(i)[2] + Quantity, this.contents.get(i)[3]};
                        this.contents.set(i, toChange);
                        this.finalQuantity += Quantity;
                        homogeneous = true;
                        break;
                    }
                }
            }
            if (!homogeneous) {
                Object[] toAdd = {Size, Matte, Quantity, Rush};
                contents.add(toAdd);
                this.finalQuantity += Quantity;
            }
            this.items = contents.size();
        }

        //        quick little method to determine if the bonus code provided is correct.
        public void setBonusCode(String code) {
            if (code.equals("N56M2")) {
                this.bonus = true;
            }
        }

        //        The meat of the Order object is the price calculation
//        it contains two different sections for if the order is homogeneous or not
        public float calcPrintPrice() {
//            I have decided to do all the calculations with integers to avoid weirdness
//            that arose from floats and doubles
            int finalPrice = 0;
            boolean Rush = false;
            if (this.contents.size() <= 1) {
//                Homogeneous order
                int basePrice = 0;
                String Size = (String) contents.get(0)[0];
                boolean Matte = (boolean) contents.get(0)[1];
                int Quantity = (int) contents.get(0)[2];
                Rush = (boolean) contents.get(0)[3];

                if (Size.equals("4x6")) {
                    basePrice = 14;
                    if (Matte) {
                        basePrice += 2;
                    }
                    for (int i = 0; i < Quantity; i++) {
                        finalPrice += basePrice;
                        if (i >= 50) {
                            finalPrice -= 2;
                        }
                        if (i >= 75) {
                            finalPrice -= 2;
                        }
                    }
                }
                if (Size.equals("5x7")) {
                    basePrice = 34;
                    if (Matte) {
                        basePrice += 3;
                    }
                    for (int i = 0; i < Quantity; i++) {
                        finalPrice += basePrice;
                        if (i >= 50) {
                            finalPrice -= 3;
                        }
                        if (i >= 75) {
                            finalPrice -= 3;
                        }
                    }
                }
                if (Size.equals("8x10")) {
                    basePrice = 68;
                    if (Matte) {
                        basePrice += 4;
                    }
                    for (int i = 0; i < Quantity; i++) {
                        finalPrice += basePrice;
                        if (i >= 50) {
                            finalPrice -= 4;
                        }
                        if (i >= 75) {
                            finalPrice -= 4;
                        }
                    }
                }


                if (Quantity == 100) {
                    if (this.bonus) {
                        if ((finalPrice>3500) || (finalPrice*.05 < 200)) {
                            finalPrice -= 200;
                        }
                        else {
                            this.bonus = false;
                        }
                    }
                }
            }
            else {
//                Non-Homogeneous order
                for (int i = 0; i < contents.size(); i++) {

                    int basePrice = 0;
                    String Size = (String) contents.get(i)[0];
                    boolean Matte = (boolean) contents.get(i)[1];
                    int Quantity = (int) contents.get(i)[2];
//                    boolean Rush = (boolean) contents.get(i)[3];
                    if ((boolean) contents.get(i)[3]){
                        Rush = true;
                    }


                    if (Size.equals("4x6")) {
                        basePrice = 19;
                        if (Matte) {
                            basePrice += 4;
                        }
                        finalPrice += basePrice * Quantity;
                    }
                    if (Size.equals("5x7")) {
                        basePrice = 39;
                        if (Matte) {
                            basePrice += 6;
                        }
                        finalPrice += basePrice * Quantity;
                    }
                    if (Size.equals("8x10")) {
                        basePrice = 79;
                        if (Matte) {
                            basePrice += 8;
                        }
                        finalPrice += basePrice * Quantity;
                    }
                }

            }
            if (Rush) {
                if (this.finalQuantity <= 60) {
                    finalPrice += 100;
                } else {
                    finalPrice += 150;
                }
            }
            if (finalPrice > 3500 && !this.bonus){
                finalPrice = (int) Math.round(finalPrice*.95);
            }

            this.finalPrice =(float) finalQuantity/100;
            return (float)finalPrice/100;
        }
    }
    public static void main (String[] args){
//      4x6 5x7 8x10
//      String Size, boolean Matte, int Quantity, boolean Rush
        Order myOrder = new Order();
        String[] validSizes = {"4x6", "5x7", "8x10"};
        String[] validBooleans = {"true", "false"};

        //    start of inputs
        if ((args.length % 4 != 0) && ((args.length-1) % 4 != 0) && args.length != 0){
//            change to (args.length % 4 != 0 or 1
            //        invalid input length
            throw new java.lang.Error("Invalid number of arguments");
        }
        else {
            for (int i = 3; i < args.length; i += 4) {
                if (!Arrays.asList(validSizes).contains(args[i - 3].toLowerCase())){
                    throw new java.lang.Error("Argument '"+args[i - 3]+"' is not a valid print size. Valid print sizes are: 4x6, 5x7, 8x10");
                }
                if (!Arrays.asList(validBooleans).contains(args[i - 2].toLowerCase())){
                    throw new java.lang.Error("Argument '"+args[i - 2]+"' is not a valid boolean for Matte. Valid booleans for Matte are: true, false");
                }
                if (!Arrays.asList(validBooleans).contains(args[i].toLowerCase())){
                    throw new java.lang.Error("Argument '"+args[i]+"' is not a valid boolean for Rush. Valid booleans for Matte are: true, false");
                }
                myOrder.addSeries((String) args[i - 3].toLowerCase(), Boolean.parseBoolean(args[i - 2].toLowerCase()), Integer.parseInt(args[i - 1]), Boolean.parseBoolean(args[i].toLowerCase()));
                if (args.length - (i + 1) == 1) {
                    myOrder.setBonusCode(args[i + 1]);
                }
            }
            //        if (args.length % 4 == 1){
            //            myOrder.setBonusCode(args[args.length-1]);
            //        }

            float output = myOrder.calcPrintPrice();
//            return myOrder.calcPrintPrice();
            System.out.println(output);
        }


    }

}



