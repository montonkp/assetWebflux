package com.example.demo;

import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.example.demo.repository.AssetRepository;
import com.example.demo.entity.Asset;

// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	// @Autowired
	// private ApplicationContext context;

	@Autowired
    private AssetRepository assetRepository;

	@Autowired
	private WebTestClient client;

	// @BeforeEach
    // public void setUp() throws Exception {
	// 	client = WebTestClient.bindToApplicationContext(context).build();
	// 	// this.client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
	// }
	
	@Test
	public void givenAssetsWhenGetAssetsThenReturnOkAssetBodyList() {
		assetRepository.saveAll(TestMock.mockAssets()).collectList().block();

		client.get().uri("/assets")
        .exchange()
        .expectStatus().isOk()
		.expectBodyList(Asset.class)
		.hasSize(6);
	}

	@Test
    public void givenAssetWhenGetAssetThenReturnOkAssetBody() {
        String mockedId = assetRepository.save(TestMock.mockAsset()).map(Asset::getAssetID).block();

        client.get().uri(String.format("/assets/%s", mockedId))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Asset.class);
	}
	
	@Test
    public void givenAssetWhenSaveAssetThenReturnCreatedAssetBody() {
        client.post().uri("/assets")
                .body(TestMock.mockAssetMono(), Asset.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Asset.class);
	}
	
	@Test
    public void givenAssetWhenUpdateAssetThenReturnOkAssetBody() {
        String mockedId = assetRepository.save(TestMock.mockAsset()).map(Asset::getAssetID).block();

        client.put().uri(String.format("/assets/%s", mockedId))
                .body(TestMock.mockAssetForUpdate(), Asset.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.assetName").isEqualTo("macbook");
	}
	
	@Test
    public void givenAssetWhenDeleteAssetThenReturnOkNoBody() {
        String mockedId = assetRepository.save(TestMock.mockAsset()).map(Asset::getAssetID).block();

        client.delete().uri(String.format("/assets/%s", mockedId))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .isEmpty();
    }
}
