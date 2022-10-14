package hr.kraljic.corona.data;

class Main {
    public static void main(String[] args) throws NoDataException {
        Data.getStatsFromYesterday().stream().forEach(System.out::println);
        Data.getStatsFromToday().stream().forEach(System.out::println);
    }
}
