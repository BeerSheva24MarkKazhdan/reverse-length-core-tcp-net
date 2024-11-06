package telran.appl.net;

import telran.net.*;

public class ReverseLengthProtocol implements Protocol {

    public static final String REQUEST_TYPE_FIELD = TcpConfigurationProperties.REQUEST_TYPE_FIELD;
    public static final String REQUEST_DATA_FIELD = TcpConfigurationProperties.REQUEST_DATA_FIELD;
    public static final String RESPONSE_CODE_FIELD = TcpConfigurationProperties.RESPONSE_CODE_FIELD;
    public static final String RESPONSE_DATA_FIELD = TcpConfigurationProperties.RESPONSE_DATA_FIELD;

    @Override
    public Response getResponse(Request request) {
        String operation = request.requestType();
        String word = request.requestData();
        ResponseCode responseCode;
        String responseData;
        switch (operation) {
            case "reverse" -> {
                responseData = new StringBuilder(word).reverse().toString();
                responseCode = ResponseCode.OK;
            }
            case "length" -> {
                responseData = String.valueOf(word.length());
                responseCode = ResponseCode.OK;
            }
            default -> {
                responseData = "Unsupported operation: " + operation;
                responseCode = ResponseCode.WRONG_TYPE;
            }
        }

        return new Response(responseCode, responseData);
    }
}