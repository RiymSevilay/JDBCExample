package com.sevilay.repository;

import com.sevilay.repository.entity.Futbolcu;
import com.sevilay.utility.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FutbolcuRepository implements ICrud<Futbolcu> {
    private String sql = "";
    private ConnectionProvider connectionProvider;
    private PreparedStatement preparedStatement;

    public FutbolcuRepository() {
        this.connectionProvider = new ConnectionProvider();
    }

    @Override
    public void save(Futbolcu futbolcu) {
        sql = "INSERT INT futbolcular (isim, mevki, forma_no, takimId) Values (?,?,?,?)";
        try {
            preparedStatement = connectionProvider.getPreparedStatement(sql);
            preparedStatement.setString(1, futbolcu.getIsim());
            preparedStatement.setString(2, futbolcu.getMevki());
            preparedStatement.setInt(3, futbolcu.getFormaNo());
            preparedStatement.setLong(4, futbolcu.getTakimId());
            preparedStatement.executeUpdate();
            connectionProvider.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Futbolcu futbolcu) {
        sql = "UPDATE futbolcular SET isim = ?, mevki = ?, forma_no = ?, deger = ?, takim_id = ? WHERE id= ?";

        try {
            preparedStatement = connectionProvider.getPreparedStatement(sql);
            preparedStatement.setString(1, futbolcu.getIsim());
            preparedStatement.setString(2, futbolcu.getMevki());
            preparedStatement.setInt(3, futbolcu.getFormaNo());
            preparedStatement.setLong(4, futbolcu.getDeger());
            preparedStatement.setLong(5, futbolcu.getTakimId());
            preparedStatement.setLong(6, futbolcu.getId());
            int etkilenenVeriler = preparedStatement.executeUpdate();
            if (etkilenenVeriler > 0) {
                System.out.println("Guncelleme Basarili!");
            } else {
                System.out.println("Guncelleme Basarisiz!");
            }
            connectionProvider.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {
        sql = "DELETE FROM futbolcular WHERE id=?";
        PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
        try {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connectionProvider.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Futbolcu> findAll() {
        sql = "SELECT * FROM futbolcular";
        Futbolcu futbolcu = null;
        List<Futbolcu> futbolcular = new ArrayList<>();
        PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
        try {
            Optional<ResultSet> resultSet = connectionProvider.getData(preparedStatement);
            while (resultSet.get().next()) {
                String isim = resultSet.get().getString(2);
                String mevki = resultSet.get().getString("mevki");
                int formaNo = resultSet.get().getInt("forma_no");
                Long deger = resultSet.get().getLong("deger");
                Long takimId = resultSet.get().getLong("takimId");
                Long id = resultSet.get().getLong("id");
                futbolcu = new Futbolcu(id, isim, mevki, formaNo, deger, takimId);
                futbolcular.add(futbolcu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return futbolcular;
  }

    @Override
    public Optional<Futbolcu> findById(Long id) {
//        sql ="SELECT * FROM futbolcular WHERE id="+id;
        Futbolcu futbolcu = null;
        sql = "SELECT * FROM futbolcular WHERE id=?";
        PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(sql);
        try {
            preparedStatement.setLong(1, id);
            Optional<ResultSet> resultSet = connectionProvider.getData(preparedStatement);
            while (resultSet.get().next()) {

                String isim = resultSet.get().getString(2); //"isim"
                String mevki = resultSet.get().getString("mevki");
                int formaNo = resultSet.get().getInt("forma_no");
                Long deger = resultSet.get().getLong("deger");
                Long takimId = resultSet.get().getLong("takimId");
                futbolcu = new Futbolcu(id, isim, mevki, formaNo, deger, takimId);
                return Optional.of(futbolcu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty(); //ya da Optional.ofNullable(futbolcu);
//        return Optional.ofNullable(futbolcu);
    }
    //Optimal koşullarda findById metodunun sadece 1 veri dönmesi gerekiyor. Dolayısıyla while kımı gereksiz olabilir,
    //ancak id'nin olası tekrar ettiği bir senaryoda bu kısım bize fazladan veri döneceği için dönen veri sayısı üzerinden DB'deki
    //kurgu hatasını da tespit edebiliriz.
}
