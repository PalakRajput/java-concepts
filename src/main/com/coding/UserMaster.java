package src.main.com.coding;

import java.util.*;

class Address {

}
public class UserMaster{
    private long id;
    private String name;
    private List<Address> address;

    public UserMaster(long id, String name, List<Address> address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserMaster other = (UserMaster) obj;
        return id == other.id;
    }
}


class OUT {
    public static void main(String[] args) {
        Map<UserMaster, String> userMap = new HashMap<>();
        UserMaster user1 = new UserMaster(1L, "user1", Arrays.asList());
        UserMaster user2 = new UserMaster(2L, "user1", Arrays.asList());
        userMap.put(user1, "data1");
        userMap.put(user2, "data2");
        System.out.println(userMap.size());
        System.out.println(user1.hashCode()+" - "+user2.hashCode());
        user1.setId(2L);
        String res = userMap.get(user1);
        System.out.println(res);
    }
}

