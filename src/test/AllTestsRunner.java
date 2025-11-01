package test;

public class AllTestsRunner {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("       UNIT TESTS EXECUTION");
        System.out.println("========================================\n");

        VegetableTest.main(args);
        SaladTest.main(args);
        VegetableRepositoryTest.main(args);
        SaladRepositoryTest.main(args);
        SaladServiceTest.main(args);
        FileManagerTest.main(args);

        System.out.println("========================================");
        System.out.println("       ALL TESTS COMPLETED");
        System.out.println("========================================");
    }
}