package me.weekbelt.naverreservation.domain.displayInfoImage;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.fileInfo.FileInfo;
import me.weekbelt.naverreservation.domain.displayInfo.DisplayInfo;

import javax.persistence.*;

@Entity
@Getter @Setter
public class DisplayInfoImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_info_id")
    private DisplayInfo displayInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;
}
