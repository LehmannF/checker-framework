// @skip-test Crashes the Checker Framework, but skipped to avoid breaking the build

import java.util.*;

public class SamFileValidator {

    private class Codec {
        public Map.Entry<String, String> decode() {
            return new AbstractMap.SimpleEntry("hello", "goodbye");
        }
    }
}
