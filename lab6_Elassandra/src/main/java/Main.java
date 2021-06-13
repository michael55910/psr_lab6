import repository.CaseRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CaseRepository caseRepository = new CaseRepository();

        //MENU
        Menu menu = new Menu();
        while (true) {
            int operation = menu.selectOperation();
            if (operation == 0) {
                return;
            }
            int target = menu.selectTarget();
            switch (operation) {
                case 1:
                    switch (target) {
                        case 1 -> caseRepository.add();
                        /*case 2 -> repairBookEntryRepository.addEntry();*/
                    }
                    break;
                case 2:
                    switch (target) {
                        case 1 -> caseRepository.updateById(getId());
                        /*case 2 -> repairBookEntryRepository.updateById(getId());*/
                    }
                    break;
                case 3:
                    switch (target) {
                        case 1 -> caseRepository.deleteById(getId());
                        /*case 2 -> repairBookEntryRepository.deleteById(getId());*/
                    }
                    break;
                case 4:
                    switch (target) {
                        case 1 -> caseRepository.getById(getId());
                        /*case 2 -> repairBookEntryRepository.getById(getId());*/
                    }
                    break;
                case 5:
                    Scanner scanner = new Scanner(System.in);
                    switch (target) {
                        case 1 -> {
                            System.out.print("Enter offence type: ");
                            caseRepository.getByType(scanner.next());
                        }
                        /*case 2 -> repairBookEntryRepository.getByDate();*/
                    }
                    break;
                case 6:
                    switch (target) {
                        case 1 -> caseRepository.getAvgVictims();
                    }
                    break;
                case 7:
                    switch (target) {
                        case 1 -> caseRepository.getAll();
                    }
                    break;
                default:
                    System.out.println("Błędny wybór!");
                    return;
            }
        }

    }

    private static Long getId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj id: ");
        return scanner.nextLong();
    }
}
