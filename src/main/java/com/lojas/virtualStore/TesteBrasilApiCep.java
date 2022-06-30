

public class TestBrasilApiCep{

    public static void main(String[] args){
        String cep = "87703550";
        String url = "http://brasilapi.com.br/api/cep/v2/"+cep;
        ResultTemplate restTemplate = new ResultTemplate();
        Object ob = restTemplate.getForObject(url, Object.class);
        System.out.println(ob);
    }
}