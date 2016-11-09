package net.biboka.transrad.transrad.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by biboka on 2016.11.08..
 */

public class AssignUtil {
    public static Map<String,String> assignmentMap;
    static {
        assignmentMap = new HashMap<>();
        assignmentMap.put("UH","Ultrahang");
        assignmentMap.put("UH2","Ultrahang 2");
        assignmentMap.put("ITO/rtg","Röntgen - ITO");
        assignmentMap.put("ÜGY","Ügyelet");
        assignmentMap.put("ÜGY2","Ügyelet 2");
        assignmentMap.put("BIOP","Biopszia");
        assignmentMap.put("IR","Intervenció");
        assignmentMap.put("CT","CT");
        assignmentMap.put("CT2","CT2");
        assignmentMap.put("oktat/vizsga","Oktatás / Vizsga");
        assignmentMap.put("MR","MR (Rad.Klin)");
    }
}
