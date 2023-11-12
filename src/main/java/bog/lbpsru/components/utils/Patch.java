package bog.lbpsru.components.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * @author Bog
 */
public class Patch {

    public String type;
    public int offset;
    public Object value;

    public Patch(String type, int offset, Object value) {
        this.type = type;
        this.offset = offset;
        this.value = value;
    }

    public static ArrayList<Patch> patch130() {
        ArrayList<Patch> patches = new ArrayList<>();
        patches.add(new Patch("utf8", 0x0084b6c8, "gamedata/alear/boot.map"));
        patches.add(new Patch("be32", 0x00010774, 0x80628070));
        patches.add(new Patch("be32", 0x00010778, 0x3c800085));
        patches.add(new Patch("be32", 0x0001077c, 0x6084c198));
        patches.add(new Patch("be32", 0x00010780, 0x90830000));
        patches.add(new Patch("be32", 0x00010784, 0x4e800020));
        patches.add(new Patch("be32", 0x00010788, 0x7c7a1b78));
        patches.add(new Patch("be32", 0x0001078c, 0x8062f568));
        patches.add(new Patch("be32", 0x00010790, 0x38800114));
        patches.add(new Patch("be32", 0x00010794, 0x4851e1c3));
        patches.add(new Patch("be32", 0x00010798, 0x7c7b1b78));
        patches.add(new Patch("be32", 0x0001079c, 0x8082f544));
        patches.add(new Patch("be32", 0x000107a0, 0x909b0000));
        patches.add(new Patch("be32", 0x000107a4, 0x38630004));
        patches.add(new Patch("be32", 0x000107a8, 0x38800000));
        patches.add(new Patch("be32", 0x000107ac, 0x3ca00084));
        patches.add(new Patch("be32", 0x000107b0, 0x60a5b6c8));
        patches.add(new Patch("be32", 0x000107b4, 0x485141e7));
        patches.add(new Patch("be32", 0x000107b8, 0x38600000));
        patches.add(new Patch("be32", 0x000107bc, 0x907b0104));
        patches.add(new Patch("be32", 0x000107c0, 0x907b0108));
        patches.add(new Patch("be32", 0x000107c4, 0x907b010c));
        patches.add(new Patch("be32", 0x000107c8, 0x907b0110));
        patches.add(new Patch("be32", 0x000107cc, 0x8062f564));
        patches.add(new Patch("be32", 0x000107d0, 0x38810074));
        patches.add(new Patch("be32", 0x000107d4, 0x93610074));
        patches.add(new Patch("be32", 0x000107d8, 0x4855063b));
        patches.add(new Patch("be32", 0x000107dc, 0xe8410028));
        patches.add(new Patch("be32", 0x000107e0, 0x7f43d378));
        patches.add(new Patch("be32", 0x000107e4, 0x48515b06));
        patches.add(new Patch("be32", 0x0031e250, 0xf821f8e1));
        patches.add(new Patch("be32", 0x0031e254, 0x3c801aea));
        patches.add(new Patch("be32", 0x0031e258, 0x60846c44));
        patches.add(new Patch("be32", 0x0031e25c, 0x90810710));
        patches.add(new Patch("be32", 0x0031e260, 0x38800001));
        patches.add(new Patch("be32", 0x0031e264, 0x90a10610));
        patches.add(new Patch("be32", 0x0031e268, 0x3880000b));
        patches.add(new Patch("be32", 0x0031e26c, 0x90810190));
        patches.add(new Patch("be32", 0x0031e270, 0x809a024c));
        patches.add(new Patch("be32", 0x0031e274, 0x8084000c));
        patches.add(new Patch("be32", 0x0031e278, 0x90810194));
        patches.add(new Patch("be32", 0x0031e27c, 0x38800000));
        patches.add(new Patch("be32", 0x0031e280, 0x90810100));
        patches.add(new Patch("be32", 0x0031e284, 0x80829f38));
        patches.add(new Patch("be32", 0x0031e288, 0x80840000));
        patches.add(new Patch("be32", 0x0031e28c, 0x80840078));
        patches.add(new Patch("be32", 0x0031e290, 0x2f840000));
        patches.add(new Patch("be32", 0x0031e294, 0x419e0018));
        patches.add(new Patch("be32", 0x0031e298, 0x80840050));
        patches.add(new Patch("be32", 0x0031e29c, 0x2f840000));
        patches.add(new Patch("be32", 0x0031e2a0, 0x419e000c));
        patches.add(new Patch("be32", 0x0031e2a4, 0x80840014));
        patches.add(new Patch("be32", 0x0031e2a8, 0x90810100));
        patches.add(new Patch("be32", 0x0031e2ac, 0x807a0238));
        patches.add(new Patch("be32", 0x0031e2b0, 0x80810100));
        patches.add(new Patch("be32", 0x0031e2b4, 0x38a10700));
        patches.add(new Patch("be32", 0x0031e2b8, 0x38c10190));
        patches.add(new Patch("be32", 0x0031e2bc, 0x38e10130));
        patches.add(new Patch("be32", 0x0031e2c0, 0x48151023));
        patches.add(new Patch("be32", 0x0031e2c4, 0x38210720));
        patches.add(new Patch("be32", 0x0031e2c8, 0x7fe3fb78));
        patches.add(new Patch("be32", 0x0031e2cc, 0x48295f0b));
        patches.add(new Patch("be32", 0x0031e2d0, 0x4831e47e));
        patches.add(new Patch("be32", 0x0031e2e0, 0x57e0063e));
        patches.add(new Patch("be32", 0x0031e2e4, 0x2f8000cb));
        patches.add(new Patch("be32", 0x0031e2e8, 0x419e0024));
        patches.add(new Patch("be32", 0x0031e2ec, 0x2f8000cc));
        patches.add(new Patch("be32", 0x0031e2f0, 0x419e0044));
        patches.add(new Patch("be32", 0x0031e2f4, 0x2f8000cd));
        patches.add(new Patch("be32", 0x0031e2f8, 0x419e006c));
        patches.add(new Patch("be32", 0x0031e2fc, 0x2f8000ce));
        patches.add(new Patch("be32", 0x0031e300, 0x419e0094));
        patches.add(new Patch("be32", 0x0031e304, 0x801e0090));
        patches.add(new Patch("be32", 0x0031e308, 0x4814bf3a));
        patches.add(new Patch("be32", 0x0031e30c, 0x812100f4));
        patches.add(new Patch("be32", 0x0031e310, 0x7beb0422));
        patches.add(new Patch("be32", 0x0031e314, 0x7be08422));
        patches.add(new Patch("be32", 0x0031e318, 0x7d695a14));
        patches.add(new Patch("be32", 0x0031e31c, 0x7d290214));
        patches.add(new Patch("be32", 0x0031e320, 0x796b0020));
        patches.add(new Patch("be32", 0x0031e324, 0x79290020));
        patches.add(new Patch("be32", 0x0031e328, 0x91690000));
        patches.add(new Patch("be32", 0x0031e32c, 0x801e0090));
        patches.add(new Patch("be32", 0x0031e330, 0x4814bf3a));
        patches.add(new Patch("be32", 0x0031e334, 0x812100f4));
        patches.add(new Patch("be32", 0x0031e338, 0x7beb0422));
        patches.add(new Patch("be32", 0x0031e33c, 0x7be08422));
        patches.add(new Patch("be32", 0x0031e340, 0x7d695a14));
        patches.add(new Patch("be32", 0x0031e344, 0x7d290214));
        patches.add(new Patch("be32", 0x0031e348, 0x796b0020));
        patches.add(new Patch("be32", 0x0031e34c, 0x79290020));
        patches.add(new Patch("be32", 0x0031e350, 0x816b0000));
        patches.add(new Patch("be32", 0x0031e354, 0x800b0000));
        patches.add(new Patch("be32", 0x0031e358, 0x90090000));
        patches.add(new Patch("be32", 0x0031e35c, 0x801e0090));
        patches.add(new Patch("be32", 0x0031e360, 0x4814bf3a));
        patches.add(new Patch("be32", 0x0031e364, 0x812100f4));
        patches.add(new Patch("be32", 0x0031e368, 0x7beb0422));
        patches.add(new Patch("be32", 0x0031e36c, 0x7be08422));
        patches.add(new Patch("be32", 0x0031e370, 0x7d695a14));
        patches.add(new Patch("be32", 0x0031e374, 0x7d290214));
        patches.add(new Patch("be32", 0x0031e378, 0x796b0020));
        patches.add(new Patch("be32", 0x0031e37c, 0x79290020));
        patches.add(new Patch("be32", 0x0031e380, 0x81290000));
        patches.add(new Patch("be32", 0x0031e384, 0x800b0000));
        patches.add(new Patch("be32", 0x0031e388, 0x90090000));
        patches.add(new Patch("be32", 0x0031e38c, 0x801e0090));
        patches.add(new Patch("be32", 0x0031e390, 0x4814bf3a));
        patches.add(new Patch("be32", 0x0031e394, 0x816100f8));
        patches.add(new Patch("be32", 0x0031e398, 0x806b0000));
        patches.add(new Patch("be32", 0x0031e39c, 0x808b0004));
        patches.add(new Patch("be32", 0x0031e3a0, 0x80ab0008));
        patches.add(new Patch("be32", 0x0031e3a4, 0x80cb000c));
        patches.add(new Patch("be32", 0x0031e3a8, 0x80eb0010));
        patches.add(new Patch("be32", 0x0031e3ac, 0x810b0014));
        patches.add(new Patch("be32", 0x0031e3b0, 0x812b0018));
        patches.add(new Patch("be32", 0x0031e3b4, 0x814b001c));
        patches.add(new Patch("be32", 0x0031e3b8, 0xc02b0020));
        patches.add(new Patch("be32", 0x0031e3bc, 0xc04b0024));
        patches.add(new Patch("be32", 0x0031e3c0, 0xc06b0028));
        patches.add(new Patch("be32", 0x0031e3c4, 0xc08b002c));
        patches.add(new Patch("be32", 0x0031e3c8, 0x38000030));
        patches.add(new Patch("be32", 0x0031e3cc, 0x7c4b00ce));
        patches.add(new Patch("be32", 0x0031e3d0, 0x7feb0676));
        patches.add(new Patch("be32", 0x0031e3d4, 0x556b023e));
        patches.add(new Patch("be32", 0x0031e3d8, 0x7d6903a6));
        patches.add(new Patch("be32", 0x0031e3dc, 0x7febc676));
        patches.add(new Patch("be32", 0x0031e3e0, 0x2f8b0001));
        patches.add(new Patch("be32", 0x0031e3e4, 0x409e000c));
        patches.add(new Patch("be32", 0x0031e3e8, 0x3c400088));
        patches.add(new Patch("be32", 0x0031e3ec, 0x60421314));
        patches.add(new Patch("be32", 0x0031e3f0, 0x4e800421));
        patches.add(new Patch("be32", 0x0031e3f4, 0x3c400087));
        patches.add(new Patch("be32", 0x0031e3f8, 0x604214a0));
        patches.add(new Patch("be32", 0x0031e3fc, 0x812100f4));
        patches.add(new Patch("be32", 0x0031e400, 0x7be08422));
        patches.add(new Patch("be32", 0x0031e404, 0x7d290214));
        patches.add(new Patch("be32", 0x0031e408, 0x79290020));
        patches.add(new Patch("be32", 0x0031e40c, 0x90690000));
        patches.add(new Patch("be32", 0x0031e410, 0x806100f8));
        patches.add(new Patch("be32", 0x0031e414, 0x38800000));
        patches.add(new Patch("be32", 0x0031e418, 0x38a00040));
        patches.add(new Patch("be32", 0x0031e41c, 0x4853606f));
        patches.add(new Patch("be32", 0x0031e420, 0x801e0090));
        patches.add(new Patch("be32", 0x0031e424, 0x4814bf3a));
        patches.add(new Patch("be32", 0x00515afc, 0x4801078a));
        patches.add(new Patch("be32", 0x0014bf34, 0x4831e2e2));
        return patches;
    }

    public static ArrayList<Patch> patch121() {
        ArrayList<Patch> patches = new ArrayList<>();
        patches.add(new Patch("utf8", 0x00946b48, "gamedata/alear/boot.map"));
        patches.add(new Patch("be32", 0x00014368, 0x806281d0));
        patches.add(new Patch("be32", 0x0001436c, 0x3c800095));
        patches.add(new Patch("be32", 0x00014370, 0x6084a748));
        patches.add(new Patch("be32", 0x00014374, 0x90830000));
        patches.add(new Patch("be32", 0x00014378, 0x4e800020));
        patches.add(new Patch("be32", 0x0001437c, 0x7c791b78));
        patches.add(new Patch("be32", 0x00014380, 0x8062d2d4));
        patches.add(new Patch("be32", 0x00014384, 0x38800114));
        patches.add(new Patch("be32", 0x00014388, 0x485f7313));
        patches.add(new Patch("be32", 0x0001438c, 0x7c7a1b78));
        patches.add(new Patch("be32", 0x00014390, 0x8082d2e0));
        patches.add(new Patch("be32", 0x00014394, 0x909a0000));
        patches.add(new Patch("be32", 0x00014398, 0x387a0004));
        patches.add(new Patch("be32", 0x0001439c, 0x38800000));
        patches.add(new Patch("be32", 0x000143a0, 0x3ca00094));
        patches.add(new Patch("be32", 0x000143a4, 0x60a56b48));
        patches.add(new Patch("be32", 0x000143a8, 0x485e7fa3));
        patches.add(new Patch("be32", 0x000143ac, 0x38600000));
        patches.add(new Patch("be32", 0x000143b0, 0x907a0104));
        patches.add(new Patch("be32", 0x000143b4, 0x907a0108));
        patches.add(new Patch("be32", 0x000143b8, 0x907a010c));
        patches.add(new Patch("be32", 0x000143bc, 0x907a0110));
        patches.add(new Patch("be32", 0x000143c0, 0x387f0040));
        patches.add(new Patch("be32", 0x000143c4, 0x38800001));
        patches.add(new Patch("be32", 0x000143c8, 0x48624e03));
        patches.add(new Patch("be32", 0x000143cc, 0xe8410028));
        patches.add(new Patch("be32", 0x000143d0, 0x809f0040));
        patches.add(new Patch("be32", 0x000143d4, 0x93440000));
        patches.add(new Patch("be32", 0x000143d8, 0x38800001));
        patches.add(new Patch("be32", 0x000143dc, 0x909f0044));
        patches.add(new Patch("be32", 0x000143e0, 0x7f23cb78));
        patches.add(new Patch("be32", 0x000143e4, 0x485e9c86));
        patches.add(new Patch("be32", 0x003bf0f0, 0xf821f8e1));
        patches.add(new Patch("be32", 0x003bf0f4, 0x3c801aea));
        patches.add(new Patch("be32", 0x003bf0f8, 0x60846c44));
        patches.add(new Patch("be32", 0x003bf0fc, 0x90810710));
        patches.add(new Patch("be32", 0x003bf100, 0x38800001));
        patches.add(new Patch("be32", 0x003bf104, 0x90a10610));
        patches.add(new Patch("be32", 0x003bf108, 0x3880000b));
        patches.add(new Patch("be32", 0x003bf10c, 0x90810190));
        patches.add(new Patch("be32", 0x003bf110, 0x809a024c));
        patches.add(new Patch("be32", 0x003bf114, 0x8084000c));
        patches.add(new Patch("be32", 0x003bf118, 0x90810194));
        patches.add(new Patch("be32", 0x003bf11c, 0x38800000));
        patches.add(new Patch("be32", 0x003bf120, 0x90810100));
        patches.add(new Patch("be32", 0x003bf124, 0x80829b4c));
        patches.add(new Patch("be32", 0x003bf128, 0x80840014));
        patches.add(new Patch("be32", 0x003bf12c, 0x80840078));
        patches.add(new Patch("be32", 0x003bf130, 0x2f840000));
        patches.add(new Patch("be32", 0x003bf134, 0x419e0018));
        patches.add(new Patch("be32", 0x003bf138, 0x80840050));
        patches.add(new Patch("be32", 0x003bf13c, 0x2f840000));
        patches.add(new Patch("be32", 0x003bf140, 0x419e000c));
        patches.add(new Patch("be32", 0x003bf144, 0x80840014));
        patches.add(new Patch("be32", 0x003bf148, 0x90810100));
        patches.add(new Patch("be32", 0x003bf14c, 0x807a0238));
        patches.add(new Patch("be32", 0x003bf150, 0x80810100));
        patches.add(new Patch("be32", 0x003bf154, 0x38a10700));
        patches.add(new Patch("be32", 0x003bf158, 0x38c10190));
        patches.add(new Patch("be32", 0x003bf15c, 0x38e10130));
        patches.add(new Patch("be32", 0x003bf160, 0x481a616b));
        patches.add(new Patch("be32", 0x003bf164, 0x38210720));
        patches.add(new Patch("be32", 0x003bf168, 0x7fe3fb78));
        patches.add(new Patch("be32", 0x003bf16c, 0x4831cbbb));
        patches.add(new Patch("be32", 0x003bf170, 0x483bf07a));
        patches.add(new Patch("be32", 0x003bf174, 0x57e0063e));
        patches.add(new Patch("be32", 0x003bf178, 0x2f8000cb));
        patches.add(new Patch("be32", 0x003bf17c, 0x419e0024));
        patches.add(new Patch("be32", 0x003bf180, 0x2f8000cc));
        patches.add(new Patch("be32", 0x003bf184, 0x419e0044));
        patches.add(new Patch("be32", 0x003bf188, 0x2f8000cd));
        patches.add(new Patch("be32", 0x003bf18c, 0x419e006c));
        patches.add(new Patch("be32", 0x003bf190, 0x2f8000ce));
        patches.add(new Patch("be32", 0x003bf194, 0x419e0094));
        patches.add(new Patch("be32", 0x003bf198, 0x801e0090));
        patches.add(new Patch("be32", 0x003bf19c, 0x481a03c6));
        patches.add(new Patch("be32", 0x003bf1a0, 0x812100f8));
        patches.add(new Patch("be32", 0x003bf1a4, 0x7beb0422));
        patches.add(new Patch("be32", 0x003bf1a8, 0x7be08422));
        patches.add(new Patch("be32", 0x003bf1ac, 0x7d695a14));
        patches.add(new Patch("be32", 0x003bf1b0, 0x7d290214));
        patches.add(new Patch("be32", 0x003bf1b4, 0x796b0020));
        patches.add(new Patch("be32", 0x003bf1b8, 0x79290020));
        patches.add(new Patch("be32", 0x003bf1bc, 0x91690000));
        patches.add(new Patch("be32", 0x003bf1c0, 0x801e0090));
        patches.add(new Patch("be32", 0x003bf1c4, 0x481a03c6));
        patches.add(new Patch("be32", 0x003bf1c8, 0x812100f8));
        patches.add(new Patch("be32", 0x003bf1cc, 0x7beb0422));
        patches.add(new Patch("be32", 0x003bf1d0, 0x7be08422));
        patches.add(new Patch("be32", 0x003bf1d4, 0x7d695a14));
        patches.add(new Patch("be32", 0x003bf1d8, 0x7d290214));
        patches.add(new Patch("be32", 0x003bf1dc, 0x796b0020));
        patches.add(new Patch("be32", 0x003bf1e0, 0x79290020));
        patches.add(new Patch("be32", 0x003bf1e4, 0x816b0000));
        patches.add(new Patch("be32", 0x003bf1e8, 0x800b0000));
        patches.add(new Patch("be32", 0x003bf1ec, 0x90090000));
        patches.add(new Patch("be32", 0x003bf1f0, 0x801e0090));
        patches.add(new Patch("be32", 0x003bf1f4, 0x481a03c6));
        patches.add(new Patch("be32", 0x003bf1f8, 0x812100f8));
        patches.add(new Patch("be32", 0x003bf1fc, 0x7beb0422));
        patches.add(new Patch("be32", 0x003bf200, 0x7be08422));
        patches.add(new Patch("be32", 0x003bf204, 0x7d695a14));
        patches.add(new Patch("be32", 0x003bf208, 0x7d290214));
        patches.add(new Patch("be32", 0x003bf20c, 0x796b0020));
        patches.add(new Patch("be32", 0x003bf210, 0x79290020));
        patches.add(new Patch("be32", 0x003bf214, 0x81290000));
        patches.add(new Patch("be32", 0x003bf218, 0x800b0000));
        patches.add(new Patch("be32", 0x003bf21c, 0x90090000));
        patches.add(new Patch("be32", 0x003bf220, 0x801e0090));
        patches.add(new Patch("be32", 0x003bf224, 0x481a03c6));
        patches.add(new Patch("be32", 0x003bf228, 0x816100fc));
        patches.add(new Patch("be32", 0x003bf22c, 0x806b0000));
        patches.add(new Patch("be32", 0x003bf230, 0x808b0004));
        patches.add(new Patch("be32", 0x003bf234, 0x80ab0008));
        patches.add(new Patch("be32", 0x003bf238, 0x80cb000c));
        patches.add(new Patch("be32", 0x003bf23c, 0x80eb0010));
        patches.add(new Patch("be32", 0x003bf240, 0x810b0014));
        patches.add(new Patch("be32", 0x003bf244, 0x812b0018));
        patches.add(new Patch("be32", 0x003bf248, 0x814b001c));
        patches.add(new Patch("be32", 0x003bf24c, 0xc02b0020));
        patches.add(new Patch("be32", 0x003bf250, 0xc04b0024));
        patches.add(new Patch("be32", 0x003bf254, 0xc06b0028));
        patches.add(new Patch("be32", 0x003bf258, 0xc08b002c));
        patches.add(new Patch("be32", 0x003bf25c, 0x38000030));
        patches.add(new Patch("be32", 0x003bf260, 0x7c4b00ce));
        patches.add(new Patch("be32", 0x003bf264, 0x7feb0676));
        patches.add(new Patch("be32", 0x003bf268, 0x556b023e));
        patches.add(new Patch("be32", 0x003bf26c, 0x7d6903a6));
        patches.add(new Patch("be32", 0x003bf270, 0x7febc676));
        patches.add(new Patch("be32", 0x003bf274, 0x2f8b0001));
        patches.add(new Patch("be32", 0x003bf278, 0x409e000c));
        patches.add(new Patch("be32", 0x003bf27c, 0x3c400097));
        patches.add(new Patch("be32", 0x003bf280, 0x6042f018));
        patches.add(new Patch("be32", 0x003bf284, 0x4e800421));
        patches.add(new Patch("be32", 0x003bf288, 0x3c400096));
        patches.add(new Patch("be32", 0x003bf28c, 0x6042f0d0));
        patches.add(new Patch("be32", 0x003bf290, 0x812100f8));
        patches.add(new Patch("be32", 0x003bf294, 0x7be08422));
        patches.add(new Patch("be32", 0x003bf298, 0x7d290214));
        patches.add(new Patch("be32", 0x003bf29c, 0x79290020));
        patches.add(new Patch("be32", 0x003bf2a0, 0x90690000));
        patches.add(new Patch("be32", 0x003bf2a4, 0x806100fc));
        patches.add(new Patch("be32", 0x003bf2a8, 0x38800000));
        patches.add(new Patch("be32", 0x003bf2ac, 0x38a00040));
        patches.add(new Patch("be32", 0x003bf2b0, 0x4860e6eb));
        patches.add(new Patch("be32", 0x003bf2b4, 0x801e0090));
        patches.add(new Patch("be32", 0x003bf2b8, 0x481a03c6));
        patches.add(new Patch("be32", 0x005e9c80, 0x4801437e));
        patches.add(new Patch("be32", 0x001a03c0, 0x483bf176));
        return patches;
    }

    public static void applyPatches(byte[] fileBytes, ArrayList<Patch> patches) {
        for (Patch patch : patches) {
            int offset = patch.offset - 0x00010000;
            Object value = patch.value;

            if (offset >= 0 && offset + getValueSize(patch) <= fileBytes.length) {
                if (patch.type.equals("utf8")) {
                    if (value instanceof String) {
                        byte[] bytes = ((String) value).getBytes();
                        System.arraycopy(bytes, 0, fileBytes, offset, bytes.length);
                    }
                } else if (patch.type.equals("be32")) {
                    if (value instanceof Integer) {
                        int intValue = (int) value;
                        byte[] bytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(intValue).array();
                        System.arraycopy(bytes, 0, fileBytes, offset, bytes.length);
                    }
                }
            }
        }
    }

    private static int getValueSize(Patch patch) {
        if (patch.type.equals("utf8")) {
            if (patch.value instanceof String) {
                return ((String) patch.value).getBytes().length;
            }
        } else if (patch.type.equals("be32")) {
            if (patch.value instanceof Integer) {
                return 4;
            }
        }
        return 0;
    }
}