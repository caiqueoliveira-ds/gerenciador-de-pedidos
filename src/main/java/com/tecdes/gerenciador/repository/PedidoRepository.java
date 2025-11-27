package com.tecdes.gerenciador.repository;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    
    import com.tecdes.gerenciador.model.entity.Pedido;
    
    public class PedidoRepository {
    
        private Map<Integer, Pedido> banco = new HashMap<>();
        private int idGenerator = 1;

        public Pedido save(Pedido pedido) {
            pedido.setId_pedido(idGenerator++);
            banco.put(pedido.getId_pedido(), pedido);
            return pedido;
        }
    
        public Pedido findById(Integer id_pedido) {
            return banco.get(id_pedido);
        }
      
        public List<Pedido> findAll() {
            return new ArrayList<>(banco.values());
        }
    
        public Pedido update(Pedido pedido) {
            if (pedido.getId_pedido() == null || !banco.containsKey(pedido.getId_pedido())) {
                return null;
            }
            banco.put(pedido.getId_pedido(), pedido);
            return pedido;
        }
    
        public boolean delete(Integer id_pedido) {
            if (banco.containsKey(id_pedido)) {
                banco.remove(id_pedido);
                return true;
            }
            return false;
        }
    }
    