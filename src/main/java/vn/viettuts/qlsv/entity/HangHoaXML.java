package vn.viettuts.qlsv.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class HangHoaXML {
    
    private List<HangHoa> listHangHoa;

    public List<HangHoa> getListHangHoa() {
        return listHangHoa;
    }

    public void setListHangHoa(List<HangHoa> listHangHoa) {
        this.listHangHoa = listHangHoa;
    }
}
