public class PizzaDetails {
//    public static void setBySize(PizzaSize size) {
//        switch (size) {
//            case SMALL:
//                pizza.setPrice(269.99);
//                pizza.setDescription("10” pizza for one person");
//                break;
//            case MEDIUM:
//                pizza.setPrice(322.99);
//                pizza.setDescription("12” pizza for two people");
//            case LARGE:
//                pizza.setPrice(394.99);
//                pizza.setDescription("15” pizza for four people");
//                break;
//            default:
//                pizza.setPrice(269.99);
//                pizza.setDescription("10” pizza for one person");
//                break;
//        }
//    }
    
    public static double getPriceBySize(PizzaSize size) {
        switch (size) {
            case SMALL:
                return 269.99;
            case MEDIUM:
                return 322.99;
            case LARGE:
                return 394.99;
            default:
                return 269.99;
        }
    }
    
    public static String getDescriptionBySize(PizzaSize size) {
        switch (size) {
            case SMALL:
                return "10” pizza for one person";
            case MEDIUM:
                return "12” pizza for two people";
            case LARGE:
                return "15” pizza for four people";
            default:
                return "10” pizza for one person";
        }
    }
}
