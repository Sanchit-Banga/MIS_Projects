package com.example.newbusbookingsystem.Service;

import com.example.newbusbookingsystem.Model.BusModel;
  import com.example.newbusbookingsystem.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    @Autowired
    BusRepository busRepository;

    public Optional<BusModel> findById(Long bid){
        return busRepository.findById(bid);
    }

    public boolean addBusDetails(BusModel busModel){
        busRepository.save(busModel);
        return true;
    }

    public boolean modifyName(Long id, String newName){
        Optional<BusModel> obj = busRepository.findById(id);

        if(obj.isEmpty() || obj==null){
            return false;
        }

        obj.get().setBusName(newName);

        busRepository.save(obj.get());

        return true;

    }


    public boolean deleteBus(Long bid) {
        Optional<BusModel> obj = busRepository.findById(bid);

        if(obj.isEmpty() || obj==null){
            return false;
        }

        busRepository.delete(obj.get());
        return true;
    }

    public List<BusModel> findAllBySrcDst(String src, String dest){
        return busRepository.findAllSrcDst(src,dest);
    }




}
