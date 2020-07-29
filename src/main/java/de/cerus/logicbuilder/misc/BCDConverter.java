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

}
