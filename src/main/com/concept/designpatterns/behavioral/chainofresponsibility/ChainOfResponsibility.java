package src.main.com.concept.designpatterns.behavioral.chainofresponsibility;


public class ChainOfResponsibility {
    public static void main(String[] args) {
        // Setup Chain of Responsibility
        RequestHandler requestHandler1 = new Type1Handler();
        RequestHandler requestHandler2 = new Type2Handler();

        requestHandler1.setNextHandler(requestHandler2);
        requestHandler1.handleRequest(new Request("Type2"));
        requestHandler1.handleRequest(new Request("Type1"));
        requestHandler1.handleRequest(new Request("Type3"));
    }
}

class Request {
    private String type;

    public Request(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

interface RequestHandler {
    void handleRequest(Request request);

    void setNextHandler(RequestHandler nextHandler);
}

class Type1Handler implements RequestHandler {
    private RequestHandler nextHandler;

    public void handleRequest(Request request) {
        if (request.getType().equals("Type1")) {
            System.out.println("Request handled by Type1Handler");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Request cannot be handled");
        }
    }

    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class Type2Handler implements RequestHandler {
    private RequestHandler nextHandler;

    public void handleRequest(Request request) {
        if (request.getType().equals("Type2")) {
            System.out.println("Request handled by Type2Handler");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Request cannot be handled");
        }
    }

    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}