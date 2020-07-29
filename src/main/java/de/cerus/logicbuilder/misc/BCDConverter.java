package de.cerus.logicbuilder.misc;

public class BCDConverter {

    private BCDConverter() {
    }

    public static boolean[] convertToSevenSegment(boolean d, boolean c, boolean b, boolean a) {
        if (!d && !c && !b && !a) {
            return new boolean[]{true, true, true, true, true, true, false};
        }
        if (!d && !c && !b && a) {
            return new boolean[]{false, true, true, false, false, false, false};
        }
        if (!d && !c && b && !a) {
            return new boolean[]{true, true, false, true, true, false, true};
        }
        if (!d && !c && b && a) {
            return new boolean[]{true, true, true, true, false, false, true};
        }
        if (!d && c && !b && !a) {
            return new boolean[]{false, true, true, false, false, true, true};
        }
        if (!d && c && !b && a) {
            return new boolean[]{true, false, true, true, false, true, true};
        }
        if (!d && c && b && !a) {
            return new boolean[]{true, false, true, true, true, true, true};
        }
        if (!d && c && b && a) {
            return new boolean[]{true, true, true, false, false, false, false};
        }
        if (d && !c && !b && !a) {
            return new boolean[]{true, true, true, true, true, true, true};
        }
        if (d && !c && !b && a) {
            return new boolean[]{true, true, true, true, false, true, true};
        }
        return new boolean[]{false, false, false, false, false, false, false};
    }

    public static boolean[] convertToBdc(int num) {
        if (num < 0 || num > 9) {
            throw new IllegalArgumentException("Num must be greater than -1 and lower than 10");
        }

        switch (num) {
            case 1:
                return new boolean[]{false, false, false, true};
            case 2:
                return new boolean[]{false, false, true, false};
            case 3:
                return new boolean[]{false, false, true, true};
            case 4:
                return new boolean[]{false, true, false, false};
            case 5:
                return new boolean[]{false, true, false, true};
            case 6:
                return new boolean[]{false, true, true, false};
            case 7:
                return new boolean[]{false, true, true, true};
            case 8:
                return new boolean[]{true, false, false, false};
            case 9:
                return new boolean[]{true, false, false, true};
            case 0:
            default:
                return new boolean[]{false, false, false, false};
        }
    }

}
