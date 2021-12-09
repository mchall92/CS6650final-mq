//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParseException;
//
//import java.lang.reflect.Type;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.Locale;
//
//class DateDeserializer implements JsonDeserializer<Date> {
//    @Override
//    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//            throws JsonParseException {
//        return Date.parse(json.getAsString(),
//                DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
//    }
//}