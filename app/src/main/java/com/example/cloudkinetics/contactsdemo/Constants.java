package com.example.cloudkinetics.contactsdemo;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
interface  Constants {

    String contact_json = "{\"contacts\":[" +
            "  {\"contact_id\":1,\"contact_number\":\"(823) 958 3409\",\"contact_name\":\"Andy Rubin\"}," +
            "  {\"contact_id\":2,\"contact_number\":\"(789) 358 3409\",\"contact_name\":\"Chat Hass\"}," +
            "  {\"contact_id\":3,\"contact_number\":\"(984) 366 3364\",\"contact_name\":\"Jeff Bezos\"}," +
            "  {\"contact_id\":4,\"contact_number\":\"(999) 372 7263\",\"contact_name\":\"Jack Ma\"}," +
            "  {\"contact_id\":5,\"contact_number\":\"(813) 627 6726\",\"contact_name\":\"Vijay Shekhar\"}," +
            "  {\"contact_id\":6,\"contact_number\":\"(732) 832 3283\",\"contact_name\":\"Narayan Krishnamurthy\"}," +
            "  {\"contact_id\":7,\"contact_number\":\"(834) 434 8938\",\"contact_name\":\"Sundar Pichai\"}," +
            "  {\"contact_id\":8,\"contact_number\":\"(972) 384 3848\",\"contact_name\":\"Sanjay Jha\"}," +
            "{\"contact_id\":9,\"contact_number\":\"(902) 374 9573\",\"contact_name\":\"Vanitha Narayanan\"}," +
            "{\"contact_id\":10,\"contact_number\":\"(863)857 3057\",\"contact_name\":\"Shantanu Narayan\"}," +
            "{\"contact_id\":11,\"contact_number\":\"(823) 973 8437\",\"contact_name\":\"Satya Nadella\"}," +
            "{\"contact_id\":12,\"contact_number\":\"(853) 739 3349\",\"contact_name\":\"Francisco D'Souza\"}," +
            "{\"contact_id\":13,\"contact_number\":\"(893) 493 7493\",\"contact_name\":\"Dinesh Paliwal\"}," +
            "{\"contact_id\":14,\"contact_number\":\"(797)434 2244\",\"contact_name\":\"Sanjay Mehrotra\"}," +
            "{\"contact_id\":15,\"contact_number\":\"(993) 232 3553\",\"contact_name\":\"Rajeev Suri\"}," +
            "{\"contact_id\":16,\"contact_number\":\"(802) 223 8929\",\"contact_name\":\"George Kurian\"}," +
            "{\"contact_id\":17,\"contact_number\":\"(740) 208 3823\",\"contact_name\":\"Ajit Jain\"}," +
            "{\"contact_id\":18,\"contact_number\":\"(893) 923 2832\",\"contact_name\":\"Neelam Dhawan\"}" +
            "]}";
			
	String URL_TWILIO_SMS = "https:api.twilio.com/2010-04-01/Accounts/" + BuildConfig.AUTH_SID + "/SMS/Messages.json";

}
