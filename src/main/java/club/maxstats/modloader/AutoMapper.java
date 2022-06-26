package club.maxstats.modloader;

public class AutoMapper {
    public static MappedClass fromVanilla(String vanillaClassName) { return new MappedClass("", vanillaClassName); }
    public static MappedClass fromLunar(String lunarClassName) { return new MappedClass(lunarClassName, ""); }

    public static MappedField getLunarField(String vanillaClassName, String vanillaFieldName) {
        return new MappedField(vanillaFieldName);
    }
    public static MappedMethod getLunarMethod(String vanillaClassName, MappedMethod vanillaMethod) {
        return new MappedMethod(vanillaMethod.getMethodName(), vanillaMethod.getMethodDesc());
    }

    public static class MappedClass {
        private String lunarName;
        private String vanillaName;

        public MappedClass(String lunarName, String vanillaName) {
            this.lunarName = lunarName;
            this.vanillaName = vanillaName;
        }

        public String getLunarName() { return this.lunarName; }
        public String getVanillaName() { return this.vanillaName; }
    }

    public static class MappedMethod {
        private String methodName;
        private String methodDesc;

        public MappedMethod(String methodName, String methodDesc) {
            this.methodName = methodName;
            this.methodDesc = methodDesc;
        }

        public String getMethodName() { return this.methodName; }
        public String getMethodDesc() { return this.methodDesc; }
        public String getParameters() { return this.methodDesc.substring(this.methodDesc.indexOf("(") + 1, this.methodDesc.indexOf(")")).trim(); }
        public String getFullName() { return this.methodName + this.methodDesc; }
    }

    public static class MappedField {
        private String fieldName;

        public MappedField(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() { return this.fieldName; }
    }
}
