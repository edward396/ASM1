package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleClaimProcessManager implements ClaimProcessManager {
    private Map<String, Claim> claims = new HashMap<>();

    public SimpleClaimProcessManager(List<Claim> initialClaims) {
        for (Claim claim : initialClaims) {
            claims.put(claim.getId(), claim);
        }
    }

    @Override
    public void add(Claim claim) {
        claims.put(claim.getId(), claim);
    }

    @Override
    public void update(String id, String status) {
        if (claims.containsKey(id)) {
            claims.get(id).setStatus(status);
        }
    }

    @Override
    public void delete(String id) {
        claims.remove(id);
    }

    @Override
    public Claim getOne(String id) {
        return claims.get(id);
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims.values());
    }
}
